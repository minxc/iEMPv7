package org.minxc.emp.system.rest.controller;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.id.UniqueIdUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.syetem2.manager.SystemTreeManager;
import org.minxc.emp.syetem2.manager.SystemTreeNodeManager;
import org.minxc.emp.syetem2.model.SystemTree;
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
 * 描述：sysTree层的controller
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:下午5:11:06
 * 版权:summer
 * </pre>
 */
@Controller
@RequestMapping("/sys/sysTree/")
public class SystemTreeController extends GenericController {
    @Autowired
    SystemTreeManager sysTreeManager;
    @Autowired
    SystemTreeNodeManager sysTreeNodeManager;

    /**
     * <pre>
     * sysTreeEdit.html的save后端
     * </pre>
     *
     * @param request
     * @param response
     * @param sysTree
     * @throws Exception
     */
    @RequestMapping("save")
    @CatchError(write2response = true, value = "保存系统树失败")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SystemTree sysTree) throws Exception {
        if (StringUtil.isEmpty(sysTree.getId())) {
            sysTree.setId(UniqueIdUtil.getSuid());
            sysTreeManager.create(sysTree);
        } else {
            sysTreeManager.update(sysTree);
        }
        writeSuccessData(response, sysTree, "保存系统树成功");
    }

    /**
     * <pre>
     * 获取sysTree的后端
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getObject")
    @CatchError(write2response = true, value = "获取sysTree异常")
    public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        String key = RequestUtil.getString(request, "key");
        SystemTree sysTree = null;
        if (StringUtil.isNotEmpty(id)) {
            sysTree = sysTreeManager.get(id);
        } else if (StringUtil.isNotEmpty(key)) {
            sysTree = sysTreeManager.getByKey(key);
        }
        writeSuccessData(response, sysTree);
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
        List<SystemTree> list = sysTreeManager.query(queryFilter);
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
    @CatchError(write2response = true, value = "删除系统树失败")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");
        for (String id : aryIds) {
            sysTreeManager.removeContainNode(id);
        }
        writeSuccessResult(response, "删除系统树成功");
    }
}
