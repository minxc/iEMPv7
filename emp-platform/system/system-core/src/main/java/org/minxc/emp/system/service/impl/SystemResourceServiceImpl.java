package org.minxc.emp.system.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.system.api.model.system.Subsystem;
import org.minxc.emp.system.api.model.system.SystemResource;
import org.minxc.emp.system.api.service.SystemResourceService;
import org.minxc.emp.system.core.manager.ResRoleManager;
import org.minxc.emp.system.core.manager.SubsystemManager;
import org.minxc.emp.system.core.manager.SysResourceManager;

/**
 * 用户系统资源服务接口
 * @author min.xianchang
 */

@Service
public class SystemResourceServiceImpl implements SystemResourceService{

	@Resource
	private SysResourceManager sysResourceManager;
	@Resource
	private SubsystemManager sybSystemManager;
	@Resource
	private ResRoleManager resRoleManager;
	
	
	@Override
	public List<Subsystem> getCurrentUserSystem() {
		return (List)sybSystemManager.getCuurentUserSystem();
	}

	@Override
	public Subsystem getDefaultSystem(String currentUserId) {
		return sybSystemManager.getDefaultSystem(currentUserId);
	}

	@Override
	public List<SystemResource> getBySystemId(String systemId) {
		return (List)sysResourceManager.getBySystemId(systemId);
	}

	@Override
	public List<SystemResource> getBySystemAndUser(String systemId, String userId) {
		return (List)sysResourceManager.getBySystemAndUser(systemId, userId);
	}

	@Override
	public Map<String, Set<String>> getUrlRoleBySystem(String systemId) {
		return resRoleManager.getUrlRoleBySystem(systemId);
	}

}
