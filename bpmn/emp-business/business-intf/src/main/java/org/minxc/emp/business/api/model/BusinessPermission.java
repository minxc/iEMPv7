package org.minxc.emp.business.api.model;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.business.api.model.permission.BusinessObjectPermission;

public interface BusinessPermission {

	String getObjType();

	String getObjVal();

	Map<String, ? extends BusinessObjectPermission> getBusObjMap();

	BusinessObjectPermission getBusObj(String boKey);
	
	public JSONObject getTablePermission(boolean isReadOnly);
	
	public JSONObject getPermission(boolean isReadOnly) ;
}
