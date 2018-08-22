package org.minxc.emp.system.rest.controller;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.id.UniqueIdUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.syetem2.manager.SystemDataSourceDefManager;
import org.minxc.emp.syetem2.model.SystemDataSourceDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <pre>
 * 描述：sysDataSourceDef层的controller
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:下午5:11:06
 * 版权:summer
 * </pre>
 */


@Controller
@RequestMapping("/sys/sysDataSourceDef/")
public class SystemDataSourceDefController extends GenericController {
    @Autowired
    private SystemDataSourceDefManager SystemDataSourceDefManager;

    /**
     * <pre>
     * 根据类路径获取类字段
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("initAttributes")
    @CatchError(write2response = true, value = "初始化属性异常")
    public void initAttributes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String classPath = RequestUtil.getString(request, "classPath");
        writeSuccessData(response, SystemDataSourceDefManager.initAttributes(classPath));
    }

    /**
     * <pre>
     * sysDataSourceDefEdit.html的save后端
     * </pre>
     *
     * @param request
     * @param response
     * @param sysDataSourceDef
     * @throws Exception
     */
    @RequestMapping("save")
    @CatchError(write2response = true, value = "保存数据源模板失败")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SystemDataSourceDef sysDataSourceDef) throws Exception {
        if (StringUtil.isEmpty(sysDataSourceDef.getId())) {
            sysDataSourceDef.setId(UniqueIdUtil.getSuid());
            SystemDataSourceDefManager.create(sysDataSourceDef);
        } else {
            SystemDataSourceDefManager.update(sysDataSourceDef);
        }

        writeSuccessData(response, sysDataSourceDef, "保存数据源模板成功");
    }

    /**
     * <pre>
     * 获取sysDataSourceDef的后端
     * 目前支持根据id 获取sysDataSourceDef
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getObject")
    @CatchError(write2response = true, value = "获取sysDataSourceDef异常")
    public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        SystemDataSourceDef sysDataSourceDef = null;
        if (StringUtil.isNotEmpty(id)) {
            sysDataSourceDef = SystemDataSourceDefManager.get(id);
        }
        writeSuccessData(response, sysDataSourceDef);
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
        List<SystemDataSourceDef> list = SystemDataSourceDefManager.query(queryFilter);
        return new PageJson(list);
    }
}
