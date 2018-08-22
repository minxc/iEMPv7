package org.minxc.emp.system.rest.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.id.UniqueIdUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.syetem2.manager.SystemDataSourceManager;
import org.minxc.emp.syetem2.model.SystemDataSourceImpl;

/**
 * <pre>
 * 描述：SystemDataSource层的controller
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:下午5:11:06
 * 版权:summer
 * </pre>
 */
@Controller
@RequestMapping("/sys/SystemDataSource/")
public class SystemDataSourceController extends GenericController {
    @Autowired
    SystemDataSourceManager SystemDataSourceManager;

    /**
     * <pre>
     * 测试连接
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("checkConnection")
    @CatchError(write2response = true, value = "连接失败")
    public void checkConnection(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String key = RequestUtil.getString(request, "key");
    	boolean connectable = false;
        try {
        	DataSource dataSource = SystemDataSourceManager.getDataSourceByKey(key);
        	Connection connection = dataSource.getConnection();
            connection.close();
            connectable = true;
        } catch (Exception e) {
        }
    	
        writeSuccessData(response, connectable, connectable ? "连接成功" : "连接失败");
    }

    /**
     * <pre>
     * SystemDataSourceEdit.html的save后端
     * </pre>
     *
     * @param request
     * @param response
     * @param SystemDataSource
     * @throws Exception
     */
    @RequestMapping("save")
    @CatchError(write2response = true, value = "保存数据源失败")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SystemDataSourceImpl SystemDataSource) throws Exception {
        if (StringUtil.isEmpty(SystemDataSource.getId())) {
            SystemDataSource.setId(UniqueIdUtil.getSuid());
            SystemDataSourceManager.create(SystemDataSource);
        } else {
            SystemDataSourceManager.update(SystemDataSource);
        }
        writeSuccessData(response, SystemDataSource, "保存数据源成功");
    }

    /**
     * <pre>
     * 获取SystemDataSource的后端
     * 目前支持根据id 获取SystemDataSource
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getObject")
    @CatchError(write2response = true, value = "获取SystemDataSource异常")
    public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        SystemDataSourceImpl SystemDataSource = null;
        if (StringUtil.isNotEmpty(id)) {
            SystemDataSource = SystemDataSourceManager.get(id);
        }
        writeSuccessData(response, SystemDataSource);
    }

    /**
     * <pre>
     * list页的后端
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("listJson")
    @ResponseBody
    public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        List<SystemDataSourceImpl> list = SystemDataSourceManager.query(queryFilter);
        return new PageJson(list);
    }

    /**
     * <pre>
     * 批量删除
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("remove")
    @CatchError(write2response = true, value = "删除数据源失败")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");
        SystemDataSourceManager.removeByIds(aryIds);
        writeSuccessResult(response, "删除数据源成功");
    }
}
