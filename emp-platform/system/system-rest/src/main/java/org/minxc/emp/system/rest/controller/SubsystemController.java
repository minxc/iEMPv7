package org.minxc.emp.system.rest.controller;


import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.id.UniqueIdUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import com.github.pagehelper.Page;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.organization.api.model.User;
import org.minxc.emp.system.core.manager.SubsystemManager;
import org.minxc.emp.system.core.model.SubsystemImpl;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <pre>
 * 描述：子系统定义 控制器类
 * </pre>
 */
@Controller
@RequestMapping("/sys/subsystem")
public class SubsystemController extends GenericController {
    @Resource
    SubsystemManager subsystemManager;

    /**
     * 子系统定义列表(分页条件查询)数据
     *
     * @param request
     * @param response
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @RequestMapping("listJson")
    public @ResponseBody
    PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<SubsystemImpl> subsystemList = (Page<SubsystemImpl>) subsystemManager.query(queryFilter);
        return new PageJson(subsystemList);
    }

    @RequestMapping("getUserSystem")
    @CatchError(write2response = true)
    public @ResponseBody
    void getUserSystem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!ContextUtil.currentUserIsAdmin()) {
            throw new BusinessException("目前仅仅支持超管操作，尚未开发分管授权功能！");
        }

        List Subsystem = subsystemManager.getAll();
        writeSuccessData(response, Subsystem);
    }

    /**
     * 子系统定义明细页面
     *
     * @param request
     * @param response
     * @return
     * @throws Exception ModelAndView
     */
    @RequestMapping("getJson")
    public @ResponseBody
    SubsystemImpl getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        if (StringUtil.isEmpty(id)) {
            return null;
        }
        SubsystemImpl subsystem = subsystemManager.get(id);
        return subsystem;
    }

    /**
     * 保存子系统定义信息
     *
     * @param request
     * @param response
     * @param subsystem
     * @throws Exception void
     * @throws
     */
    @RequestMapping("save")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SubsystemImpl subsystem) throws Exception {
        String resultMsg = null;

        boolean isExist = subsystemManager.isExist(subsystem);
        if (isExist) {
            resultMsg = "别名子系统中已存在!";
        //    writeResultMessage(response.getWriter(), resultMsg, ResultMsg.FAIL);
            return;
        }

        String id = subsystem.getId();
        try {
            if (StringUtil.isEmpty(id)) {
                subsystem.setId(UniqueIdUtil.getSuid());
                User user = ContextUtil.getCurrentUser();
                subsystem.setCreator(user.getFullname());
                subsystem.setCreatorId(user.getUserId());
                subsystemManager.create(subsystem);
                resultMsg = "添加子系统定义成功";
            } else {
                subsystemManager.update(subsystem);
                resultMsg = "更新子系统定义成功";
            }
      //      writeResultMessage(response.getWriter(), resultMsg, ResultMsg.SUCCESS);
        } catch (Exception e) {
            resultMsg = "对子系统定义操作失败";
    //        writeResultMessage(response.getWriter(), resultMsg, e.getMessage(), ResultMsg.FAIL);
        }
    }

    /**
     * 批量删除子系统定义记录
     *
     * @param request
     * @param response
     * @throws Exception void
     * @throws
     */
    @RequestMapping("remove")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultMsg message = null;
        try {
            String[] aryIds = RequestUtil.getStringAryByStr(request, "id");
            subsystemManager.removeByIds(aryIds);
            message = new ResultMsg(ResultMsg.SUCCESS, "删除子系统定义成功");
        } catch (Exception e) {
            message = new ResultMsg(ResultMsg.FAIL, "删除子系统定义失败");
        }
        writeResultMessage(response.getWriter(), message);
    }
}
