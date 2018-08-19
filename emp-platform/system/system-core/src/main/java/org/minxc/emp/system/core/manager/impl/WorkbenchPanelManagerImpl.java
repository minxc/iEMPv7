package org.minxc.emp.system.core.manager.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.model.PageList;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.core.util.AppUtil;
import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.model.query.DefaultQueryFilter;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.system.api.constant.RightsObjectConstants;
import org.minxc.emp.system.core.dao.WorkbenchPanelDao;
import org.minxc.emp.system.core.manager.SystemAuthorizationManager;
import org.minxc.emp.system.core.manager.WorkbenchLayoutManager;
import org.minxc.emp.system.core.manager.WorkbenchPanelManager;
import org.minxc.emp.system.core.model.WorkbenchLayout;
import org.minxc.emp.system.core.model.WorkbenchPanel;
import org.minxc.emp.system.util.ContextUtil;


@Service("workbenchPanelManager")
public class WorkbenchPanelManagerImpl extends BaseManager<String, WorkbenchPanel> implements WorkbenchPanelManager {
    @Resource
    private WorkbenchPanelDao workbenchPanelDao;
    @Resource
    private  WorkbenchLayoutManager workbenchLayoutManager;

    @Resource
    private  SystemAuthorizationManager sysAuthorizationManager;


    @Override
    public List<WorkbenchPanel> getByUserId(String userId) {
        Map<String, Object> userPermission = sysAuthorizationManager.getUserRightsSql(RightsObjectConstants.WORKBENCH, userId, "p.id_");
        userPermission.put("userId", userId);

        List<WorkbenchPanel> layOut = workbenchPanelDao.getByUser(userPermission);

        if (BeanUtils.isEmpty(layOut)) {
            userPermission.put("userId", WorkbenchLayout.DEFAULT_LAYOUT);
            layOut = workbenchPanelDao.getByUser(userPermission);
        }

        return layOut;
    }


    @Override
    public List<WorkbenchPanel> getBylayoutKey(String layoutKey) {
        return workbenchPanelDao.getBylayoutKey(layoutKey);
    }

    @Override
    public List<WorkbenchPanel> getMyUsablePanels(QueryFilter query) {
        //获取默认的布局
        String layoutKey = (String) query.getParams().get("layoutKey");
        if (StringUtil.isNotEmpty(layoutKey)) {
            return workbenchPanelDao.query();
        }

        String userId = ContextUtil.getCurrentUserId();
        Map<String, Object> userPermission = sysAuthorizationManager.getUserRightsSql(RightsObjectConstants.WORKBENCH, userId, null);
        query.getParams().putAll(userPermission);

        DefaultQueryFilter queryFilter = (DefaultQueryFilter) query;
        queryFilter.setPage(null);

        return workbenchPanelDao.getUsablePanelsByUserRight(queryFilter);
    }

    @Override
    public JSON getPanelData(Map<String, String> requstParam) {

        return null;
    }


    @Override
    public JSON getDataByInterFace(QueryFilter filter, String dataSource) {

        if (StringUtil.isEmpty(dataSource)) throw new RuntimeException("自定义对话框数据服务接口不能为空！");

        String[] aryHandler = dataSource.split("[.]");
        if (aryHandler == null || aryHandler.length != 2) throw new RuntimeException("自定义对话框数据服务接口格式不正确！" + dataSource);
        ;

        String beanId = aryHandler[0];
        String method = aryHandler[1];

        // 触发该Bean下的业务方法
        Object serviceBean = AppUtil.getBean(beanId);
        if (serviceBean == null) return null;
        Object objct = null;
        try {
            objct = invokeMethod(serviceBean, method, filter);
        } catch (Exception e) {
            throw new RuntimeException("查询异常！" + e.getMessage(), e);
        }

        if (objct instanceof PageList) {
        }

        return (JSON) JSON.toJSON(objct);
    }


    private Object invokeMethod(Object serviceBean, String method, QueryFilter filter) throws SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class clazz = serviceBean.getClass();

        Method invokeMethod = null;
        Object o = null;

        try {
            invokeMethod = clazz.getMethod(method);
            o = invokeMethod.invoke(serviceBean);

        } catch (NoSuchMethodException e) {
        }
        if (invokeMethod == null) {
            try {
                invokeMethod = clazz.getMethod(method, QueryFilter.class);
                o = invokeMethod.invoke(serviceBean, filter);
            } catch (NoSuchMethodException e) {
                throw new BusinessException(String.format("被调用方法%s.%s 入参为QueryFilter,或者为空", serviceBean, method));
            }
        }

        if (void.class == invokeMethod.getReturnType()) {
            throw new BusinessException(String.format("被调用方法%s.%s 返回值不能为void！", serviceBean, method));
        }

        return o;
    }

    /**
     * 测试用
     *
     * @return
     */
    public JSON getTestData() {

        String json = "[[\"product\", \"纯牛奶\", \"咖啡\", \"矿泉水\"]," // 产品项
                + "[\"2015\"," + getRandomInt() + ", " + getRandomInt() + ", " + getRandomInt() + "], [\"2016\", " + getRandomInt() + ", " + getRandomInt() + ", " + getRandomInt() + "],"
                + "[\"2017\", " + getRandomInt() + ", " + getRandomInt() + ", " + getRandomInt() + "], [\"2018\"," + getRandomInt() + ", " + getRandomInt() + ", " + getRandomInt() + "]]";
        JSONArray jsonArray = JSONArray.parseArray(json);

        return jsonArray;
    }

    /**
     * 测试用
     *
     * @return
     */
    public JSON getPieData() {
        String json = "[[\"纯牛奶\"," + getRandomInt() + "]," // 产品项
                + "[\"咖啡\"," + getRandomInt() + "],"
                + "[\"矿泉水\"," + getRandomInt() + "],"
                + "[\"碳酸饮料\"," + getRandomInt() + "]]";
        JSONArray jsonArray = JSONArray.parseArray(json);
        return jsonArray;
    }

    private int getRandomInt() {
        Random rand = new Random();
        return rand.nextInt(6000);
    }

}
