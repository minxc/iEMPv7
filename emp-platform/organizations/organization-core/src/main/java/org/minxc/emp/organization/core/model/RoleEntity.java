package org.minxc.emp.organization.core.model;

import org.minxc.emp.base.core.model.AbstractCommonModel;
import org.minxc.emp.organization.api.model.Group;
import org.minxc.emp.organization.api.model.GroupStructEnum;
import org.minxc.emp.organization.api.model.IdentityType;

/**
 * @version V1.0
 * @Title: RoleEntity
 * @Package: org.minxc.emp.organization.core.model
 * @Description: 系统角色实体类
 * @author: Xianchang.min
 * @date 2018/8/22 19:58
 */

public class RoleEntity extends AbstractCommonModel implements Group{


    /** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = 2246565560385511489L;

	
	private String name;

    private String alias;

    private Long enabled;

    private String description;

	@Override
	public String getIdentityType() {
		return IdentityType.GROUP.);
	}

	@Override
	public String getGroupId() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getGroupCode() {
		return null;
	}

	@Override
	public Long getSn() {
		return null;
	}

	@Override
	public String getGroupType() {
		return null;
	}

	@Override
	public GroupStructEnum getStruct() {
		return null;
	}

	@Override
	public String getParentId() {
		return null;
	}

	@Override
	public String getPath() {
		return null;
	}

}
