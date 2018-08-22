package org.minxc.emp.syetem2.permission.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.system.api2.permission.PermissionCalculator;
import org.minxc.emp.system.util.ContextUtil;

/**
 * <pre>
 * 描述：用户
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年5月8日
 * 版权:summer
 * </pre>
 */
@Service
public class UsersPermissionCalculator implements PermissionCalculator {

	@Override
	public String getTitle() {
		return "用户";
	}

	@Override
	public String getType() {
		return "user";
	}

	@Override
	public boolean haveRights(JSONObject json) {
		for(String id :json.getString("id").split(",")) {
			if(id.equals(ContextUtil.getCurrentUserId())) {
				return true;
			}
		}
		return false;
	}

}
