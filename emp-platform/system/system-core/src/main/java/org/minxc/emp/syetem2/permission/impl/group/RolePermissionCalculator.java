package org.minxc.emp.syetem2.permission.impl.group;

import org.springframework.stereotype.Service;

import org.minxc.emp.syetem2.permission.impl.GroupPermissionCalculator;
/**
 * <pre>
 * 描述：角色
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年7月12日
 * 版权:summer
 * </pre>
 */
@Service
public class RolePermissionCalculator extends GroupPermissionCalculator {
	
	@Override
	public String getType() {
		return "role";
	}

	@Override
	public String getTitle() {
		return "角色";
	}
}
