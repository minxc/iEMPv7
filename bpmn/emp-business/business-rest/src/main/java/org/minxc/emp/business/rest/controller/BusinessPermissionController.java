package org.minxc.emp.business.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.rest.BaseController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.business.manager.BusinessObjectManager;
import org.minxc.emp.business.manager.BusinessPermissionManager;
import org.minxc.emp.business.model.BusinessObject;
import org.minxc.emp.business.model.BusinessPermission;

/**
 * <pre>
 * 描述：businessPermission层的controller
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:下午5:11:06
 * 版权:summer
 * </pre>
 */
@RestController
@RequestMapping("/bus/businessPermission/")
public class BusinessPermissionController extends BaseController<BusinessPermission> {
	@Resource
	private BusinessObjectManager businessObjectManager;
	@Autowired
	private BusinessPermissionManager businessPermissionManager;

	/**
	 * <pre>
	 * 获取businessPermission的后端
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getObject")
	@CatchError(write2response = true, value = "获取businessPermission异常")
	public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String objType = RequestUtil.getString(request, "objType");
		String objVal = RequestUtil.getString(request, "objVal");
		BusinessPermission businessPermission = businessPermissionManager.getByObjTypeAndObjVal(objType, objVal);
		writeSuccessData(response, businessPermission);
	}

	/**
	 * <pre>
	 * 获取bo数据的后端
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getBo")
	@CatchError(write2response = true, value = "获取boo异常")
	public void getBo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] boKeys = RequestUtil.getStringAryByStr(request, "boKeys");
		
		Map<String, BusinessObject> boMap = new HashMap<>();
		for (String boKey : boKeys) {
			BusinessObject bo = businessObjectManager.getFilledByKey(boKey);
			boMap.put(boKey, bo);
		}
		writeSuccessData(response, boMap);
	}

	@Override
	protected String getModelDesc() {
		return "业务对象权限";
	}

}
