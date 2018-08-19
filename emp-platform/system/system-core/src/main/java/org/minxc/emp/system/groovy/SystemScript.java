package org.minxc.emp.system.groovy;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import org.minxc.emp.organization.api.model.User;
import org.minxc.emp.system.api.groovy.Script;
import org.minxc.emp.system.api.service.SerialNoService;
import org.minxc.emp.system.util.ContextUtil;

/**
 * 系统脚本
 * 常用的系统功能的脚本
 */
@Component
public class SystemScript implements Script {
	@Resource
	SerialNoService serialNoService;
	
	/**
	 * 获取系统流水号
	 * @param alias
	 * @return
	 */
	public String getNextSerialNo(String alias) {
		return serialNoService.genNextNo(alias);
	}
	
	
	public User getCuurentUser() {
		return ContextUtil.getCurrentUser();
	}
	
	
	
}
