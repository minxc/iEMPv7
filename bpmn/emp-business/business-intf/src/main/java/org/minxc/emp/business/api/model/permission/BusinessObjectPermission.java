package org.minxc.emp.business.api.model.permission;

import java.util.Map;

public interface BusinessObjectPermission extends AbstractPermission {

	String getKey();

	String getName();

	Map<String, ? extends BusinessTablePermission> getTableMap();

}
