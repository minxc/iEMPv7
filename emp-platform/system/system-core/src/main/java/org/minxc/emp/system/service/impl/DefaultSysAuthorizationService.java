package org.minxc.emp.system.service.impl;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.system.api.constant.RightsObjectConstants;
import org.minxc.emp.system.api.service.SysAuthorizationService;
import org.minxc.emp.system.core.manager.SystemAuthorizationManager;
@Service
public class DefaultSysAuthorizationService implements SysAuthorizationService{
@Resource
SystemAuthorizationManager sysAuthorizationManager;

	@Override
	public Set<String> getUserRights(String userId) {
		return sysAuthorizationManager.getUserRights(userId);
	}

	@Override
	public Map<String, Object> getUserRightsSql(RightsObjectConstants rightsObject, String userId, String targetKey) {
		return sysAuthorizationManager.getUserRightsSql(rightsObject, userId, targetKey);
	}

}
