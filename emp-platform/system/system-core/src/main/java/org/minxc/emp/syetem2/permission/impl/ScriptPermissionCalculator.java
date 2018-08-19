package org.minxc.emp.syetem2.permission.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.system.api.groovy.GroovyScriptEngine;
import org.minxc.emp.system.api2.permission.PermissionCalculator;

/**
 * <pre>
 * 描述：脚本
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:2018年5月8日
 * 版权:summer
 * </pre>
 */
@Service
public class ScriptPermissionCalculator implements PermissionCalculator {
	@Resource
	private GroovyScriptEngine groovyScriptEngine;

	@Override
	public String getTitle() {
		return "脚本";
	}

	@Override
	public String getType() {
		return "script";
	}

	@Override
	public boolean haveRights(JSONObject json) {
		String script = json.getString("id");
		return groovyScriptEngine.executeBoolean(script, null);
	}

}
