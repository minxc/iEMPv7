package org.minxc.emp.syetem2.permission.impl.group;

import org.springframework.stereotype.Service;

import org.minxc.emp.syetem2.permission.impl.GroupPermissionCalculator;
/**
 * <pre>
 * 描述：岗位
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年7月12日
 * 版权:summer
 * </pre>
 */
@Service
public class PostPermissionCalculator extends GroupPermissionCalculator {
	
	@Override
	public String getType() {
		return "post";
	}

	@Override
	public String getTitle() {
		return "岗位";
	}
}
