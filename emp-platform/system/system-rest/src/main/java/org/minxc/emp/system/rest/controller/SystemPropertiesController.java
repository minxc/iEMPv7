package org.minxc.emp.system.rest.controller;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.id.UniqueIdUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.system.core.manager.SystemPropertiesManager;
import org.minxc.emp.system.core.model.SystemProperties;
import com.github.pagehelper.Page;


/**
 * 系统属性
 */
@Controller
@RequestMapping("/sys/sysProperties")
public class SystemPropertiesController extends GenericController {
    @Resource
    SystemPropertiesManager sysPropertiesManager;

    /**
     * 系统属性列表(分页条件查询)数据
     */
    @RequestMapping("listJson")
    public @ResponseBody
    PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<SystemProperties> sysPropertiesList = (Page<SystemProperties>) sysPropertiesManager.query(queryFilter);
        return new PageJson(sysPropertiesList);
    }


    /**
     * 系统属性明细页面
     */
    @RequestMapping("getJson")
    @CatchError(write2response = true)
    public void getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        SystemProperties sysProperties = new SystemProperties();
        List<String> groups = sysPropertiesManager.getGroups();
        if (StringUtil.isEmpty(id)) {
            sysProperties.setCategorys(groups);
            writeSuccessData(response, sysProperties);
            return;
        }
        sysProperties = sysPropertiesManager.get(id);
        sysProperties.setCategorys(groups);
        writeSuccessData(response, sysProperties);
    }

    /**
     * 保存系统属性信息
     *
     * @param request
     * @param response
     * @param sysProperties
     * @throws Exception void
     * @throws
     */
    @RequestMapping("save")
    @CatchError("对系统属性操作失败")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SystemProperties sysProperties) throws Exception {
        String resultMsg = null;

        boolean isExist = sysPropertiesManager.isExist(sysProperties);
        if (isExist) {
            throw new BusinessException("别名系统中已存在!");
        }

        String id = sysProperties.getId();
        sysProperties.setValByEncrypt();

        if (StringUtil.isEmpty(id)) {
            sysProperties.setId(UniqueIdUtil.getSuid());
            sysProperties.setCreateTime(new Date());
            sysPropertiesManager.create(sysProperties);
            resultMsg = "添加系统属性成功";
        } else {
            sysPropertiesManager.update(sysProperties);
            resultMsg = "更新系统属性成功";
        }

        sysPropertiesManager.reloadProperty();
        writeSuccessResult(response, resultMsg);
    }

    /**
     * 批量删除系统属性记录
     */
    @RequestMapping("remove")
    @CatchError("删除系统属性失败")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");

        sysPropertiesManager.removeByIds(aryIds);
        writeSuccessResult(response, "删除系统属性成功");
    }
}
