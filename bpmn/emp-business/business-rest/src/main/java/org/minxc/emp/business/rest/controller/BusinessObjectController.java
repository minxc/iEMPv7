package org.minxc.emp.business.rest.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.rest.BaseController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.business.manager.BusinessObjectManager;
import org.minxc.emp.business.model.BusinessObject;

/**
 * <pre>
 * 描述：businessObject层的controller
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:下午5:11:06
 * 版权:summer
 * </pre>
 */
@RestController
@RequestMapping("/bus/businessObject/")
public class BusinessObjectController extends BaseController<BusinessObject> {
	@Resource
	BusinessObjectManager businessObjectManager;

	/**
	 * <pre>
	 * 获取businessObject的后端
	 * 目前支持根据id,key 获取businessObject
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getObject")
	@CatchErr(write2response = true, value = "获取businessObject异常")
	public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getString(request, "id");
		String key = RequestUtil.getString(request, "key");
		boolean fill = RequestUtil.getBoolean(request, "fill");// 是否要填充table
		BusinessObject object = null;
		if (StringUtil.isNotEmpty(id)) {
			object = businessObjectManager.get(id);
		} else if (StringUtil.isNotEmpty(key)) {
			object = businessObjectManager.getByKey(key);
		}
		if (fill && object != null) {
			object = businessObjectManager.getFilledByKey(object.getKey());
		}

		writeSuccessData(response, object);
	}

	@Override
	protected String getModelDesc() {
		return "业务对象";
	}

}
