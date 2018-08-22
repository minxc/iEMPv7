package org.minxc.emp.organization.core.model;

import org.minxc.emp.base.core.model.AbstractCommonModel;

/**
 * @version V1.0
 * @Title: RoleEntity
 * @Package: org.minxc.emp.organization.core.model
 * @Description: 系统角色实体类
 * @author: Xianchang.min
 * @date 2018/8/22 19:58
 */

public class RoleEntity extends AbstractCommonModel implements Role{


    /** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = 2246565560385511489L;

	
	private String name;

    private String alias;

    private Long enabled;

    private String description;

}
