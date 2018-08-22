package org.minxc.emp.syetem2.permission.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.system.api2.permission.PermissionCalculator;
/**
 * <pre>
 * 描述：无人
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年5月8日
 * 版权:summer
 * </pre>
 */
@Service
public class NonePermissionCalculator implements PermissionCalculator {

	@Override
	public String getTitle() {
		return "无";
	}

	@Override
	public String getType() {
		return "none";
	}

	@Override
	public boolean haveRights(JSONObject json) {
		return false;
	}

}
