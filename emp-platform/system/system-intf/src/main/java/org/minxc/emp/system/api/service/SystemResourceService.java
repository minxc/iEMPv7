package org.minxc.emp.system.api.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.minxc.emp.system.api.model.system.Subsystem;
import org.minxc.emp.system.api.model.system.SystemResource;

public interface SystemResourceService {

	List<Subsystem> getCurrentUserSystem();

	Subsystem getDefaultSystem(String currentUserId);

	List<SystemResource> getBySystemId(String systemId);

	List<SystemResource> getBySystemAndUser(String systemId, String userId);

	Map<String, Set<String>> getUrlRoleBySystem(String systemId);

}
