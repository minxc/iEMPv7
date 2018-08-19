package org.minxc.emp.system.api.service;

import java.util.List;

import org.minxc.emp.organization.api.model.User;
import org.minxc.emp.system.api.model.SystemIdentity;

public interface SysIdentityConvert {
	
	/**
	 *  identity type 必须为 user
	 * @param identity
	 * @return
	 */
	public User convert2User(SystemIdentity identity);
	
	public List<User> convert2Users(SystemIdentity identity);
	
	public List<User> convert2Users(List<SystemIdentity> identity);
	
}
