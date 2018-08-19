package org.minxc.emp.system.rest.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.system.api.constant.RightsObjectConstants;
import org.minxc.emp.system.core.manager.SystemAuthorizationManager;
import org.minxc.emp.system.core.model.SysAuthorization;


 
@RestController
@RequestMapping("/sys/authorization")
public class SystemAuthorizationController extends GenericController{
	@Resource
	SystemAuthorizationManager sysAuthorizationManager;
	
	/**
	 * 保存授权结果
	 * @param request
	 * @param response
	 * @param commonAuthorization
	 * @throws Exception
	 */
	@CatchError("对通用资源授权配置操作失败")
	@RequestMapping("saveAuthorization")
	public void saveAuthorization(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String targetId = RequestUtil.getString(request, "rightsTarget");
		String targetObject = RequestUtil.getString(request, "rightsObject");
		String authorizationJson = RequestUtil.getString(request, "authorizationJson");
			
		RightsObjectConstants.getByKey(targetObject);
		
		List<SysAuthorization> sysAuthorizationList = JSON.parseArray(authorizationJson, SysAuthorization.class);
		
		sysAuthorizationManager.createAll(sysAuthorizationList,targetId,targetObject);
		
		writeSuccessResult(response, "授权成功");
	}
	
	/**
	 * 获取授权结果用来初始化
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getAuthorizations")
	public void getAuthorizations(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String rightsTarget = request.getParameter("rightsTarget");
		String rightsTargetObject = RequestUtil.getString(request, "rightsObject");
		
		List<SysAuthorization> list = sysAuthorizationManager.getByTarget(RightsObjectConstants.valueOf(rightsTargetObject), rightsTarget);
		 
		writeSuccessData(response, list);
	}
}
