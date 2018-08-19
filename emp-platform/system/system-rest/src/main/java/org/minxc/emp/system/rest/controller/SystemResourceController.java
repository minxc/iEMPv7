package org.minxc.emp.system.rest.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.system.core.manager.RelResourceManager;
import org.minxc.emp.system.core.manager.SubsystemManager;
import org.minxc.emp.system.core.manager.SysResourceManager;
import org.minxc.emp.system.core.model.SubsystemImpl;
import org.minxc.emp.system.core.model.SysResource;
import com.github.pagehelper.Page;


/**
 * 子系统资源 控制器类
 */
@RestController
@RequestMapping("/sys/sysResource")
public class SystemResourceController extends GenericController {
    @Resource
    private SysResourceManager sysResourceManager;

    @Resource
    private RelResourceManager relResourceManager;

    @Resource
    private SubsystemManager subsystemManager;

    /**
     * 子系统资源列表(分页条件查询)数据
     *
     * @param request
     * @param response
     * @return
     * @throws Exception PageJson
     * @throws
     */
    @RequestMapping("listJson")
    public  PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<SysResource> sysResourceList = (Page<SysResource>) sysResourceManager.query(queryFilter);
        return new PageJson(sysResourceList);
    }


    /**
     * 子系统资源明细页面
     *
     * @param request
     * @param response
     * @return
     * @throws Exception ModelAndView
     */
    @RequestMapping("getJson")
    public  void getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        if (StringUtil.isEmpty(id)) {
            String parentId = RequestUtil.getString(request, "parentId");
            String sysytemId = RequestUtil.getString(request, "systemId");
            SysResource sysResource = new SysResource();
            sysResource.setSystemId(sysytemId);
            sysResource.setParentId(parentId);
            sysResource.setNewWindow(0);
            sysResource.setHasChildren(1);
            sysResource.setOpened(1);
            sysResource.setEnableMenu(1);
            writeSuccessData(response, sysResource);
        } else {
            SysResource sysResource = sysResourceManager.getByResId(id);
            writeSuccessData(response, sysResource);
        }
    }

    /**
     * 保存子系统资源信息
     *
     * @param sysResource
     * @throws Exception void
     * @throws
     */
    @RequestMapping("save")
    @CatchError
    public ResultMsg<String> save(@RequestBody SysResource sysResource) throws Exception {
        String resultMsg = null;
        String id = sysResource.getId();
        boolean isExist = sysResourceManager.isExist(sysResource);
        if (isExist) {
           throw new BusinessException("资源已经存在,请修改重新添加!");
        }
        
        if (StringUtil.isEmpty(id)) {
            sysResource.setSn(System.currentTimeMillis());
            sysResourceManager.create(sysResource);
            resultMsg = "添加子系统资源成功";
        } else {
            sysResourceManager.update(sysResource);
            resultMsg = "更新子系统资源成功";
        }
        
        return getSuccessResult(sysResource.getId(), resultMsg);
        
    }

    /**
     * 批量删除子系统资源记录
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
            String id = RequestUtil.getString(request, "id");
            sysResourceManager.removeByResId(id);
            message = new ResultMsg(ResultMsg.SUCCESS, "删除子系统资源成功");
        } catch (Exception e) {
            message = new ResultMsg(ResultMsg.FAIL, "删除子系统资源失败");
        }
        writeResultMessage(response.getWriter(), message);
    }
	
	/*@RequestMapping("sysResourceEdit")
	public ModelAndView sysResourceEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String parentId = request.getParameter("parentId");
		String id = request.getParameter("id");
		String parentsysResourceName = "子系统资源管理";
		if (id == null && parentId != null && !parentId.equals("0")) {
			// 新增时
			parentsysResourceName = sysResourceManager.get(parentId).getName();
		}
		return getAutoView().addObject("id", id)
				.addObject("parentId", parentId).addObject("parentsysResourceName", parentsysResourceName);
	}
	*/


    @RequestMapping("sysResourceGet")
    @CatchError(value = "获取资源失败", write2response = true)
    public void sysResourceGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        SysResource sysResource = sysResourceManager.get(id);
        writeSuccessData(response, sysResource);
    }
	
/*	
	@RequestMapping("sysResourceList")
	public ModelAndView sysResourceList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Subsystem> subsystemList=subsystemManager.getAll();
		return getAutoView().addObject("subsystemList",subsystemList);
	}*/


    @RequestMapping("getTreeData")
    @CatchError
    public List<SysResource> getTreeData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String systemId = RequestUtil.getString(request, "systemId");
        SubsystemImpl subsystem = subsystemManager.get(systemId);
        List<SysResource> groupList = getGroupTree(systemId);
        if (BeanUtils.isEmpty(groupList))
            groupList = new ArrayList<SysResource>();
        SysResource rootResource = new SysResource();
        rootResource.setName(subsystem.getName());
        rootResource.setId("0");
        rootResource.setSystemId(systemId); // 根节点
        rootResource.setHasChildren(1);
        groupList.add(rootResource);
        return groupList;
    }

    private List<SysResource> getGroupTree(String systemId) {
        List<SysResource> groupList = sysResourceManager.getBySystemId(systemId);
        return groupList;
    }

    @RequestMapping("moveResource")
    public void moveResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultMsg message = null;
        try {
            String id = RequestUtil.getString(request, "id");
            SysResource sysResource = sysResourceManager.get(id);
            String parentId = RequestUtil.getString(request, "parentId");

            SysResource parentResource = sysResourceManager.get(parentId);
            if (parentResource != null) {
                parentResource.setHasChildren(1);
                sysResourceManager.update(parentResource);
            }
            sysResource.setParentId(parentId);
            sysResourceManager.update(sysResource);
            message = new ResultMsg(ResultMsg.SUCCESS, "移动资源成功");
        } catch (Exception e) {
            message = new ResultMsg(ResultMsg.FAIL, "移动资源失败");
        }
        writeResultMessage(response.getWriter(), message);
    }

}
