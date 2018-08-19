package org.minxc.emp.syetem2.permission.impl;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.base.core.util.AppUtil;
import org.minxc.emp.base.core.util.ThreadMapUtil;
import org.minxc.emp.organization.api.model.Group;
import org.minxc.emp.organization.api.service.GroupService;
import org.minxc.emp.system.api2.permission.PermissionCalculator;
import org.minxc.emp.system.util.ContextUtil;
/**
 * <pre>
 * 描述：组 抽象类
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:2018年5月8日
 * 版权:summer
 * </pre>
 */
public abstract class GroupPermissionCalculator implements PermissionCalculator {
	/**
	 * 线程变量ThreadMapUtil中关于当前权限解析器的线程变量
	 */
	private static String threadMapKey = "org.minxc.emp.sys.permission.impl.GroupPermission";
	
	@Override
	public boolean haveRights(JSONObject json) {
		Map<String, List<Group>> allGroup = (Map<String, List<Group>>) ThreadMapUtil.get(threadMapKey);
		if(allGroup ==null) {
			GroupService groupService = AppUtil.getBean(GroupService.class);
			allGroup = groupService.getAllGroupByUserId(ContextUtil.getCurrentUserId());
			ThreadMapUtil.put(threadMapKey, allGroup);
		}
		
		List<Group> groups;
		if("post".equals(this.getType())) {//岗位的命名不一致
			groups = allGroup.get("position");
		}else {
			groups = allGroup.get(this.getType());
		}
		
		for(Group group:groups) {
			if(json.getString("id").contains(group.getGroupId())) {
				return true;
			}
		}
		
		return false;
	}
	
}
