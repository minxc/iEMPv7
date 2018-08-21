package org.minxc.emp.organization.api.model;

import java.io.Serializable;

/* 

* @Title:

* @Description:用户的标识类型

**/ 
public interface IdentityType extends Serializable {

    /**
     * 用户
     */
    public static final String USER = "user";

    /**
     * 用户组
     */
    public static final String GROUP = "group";

    /**
     * 返回用户标识类型
     *
     * @return String
     */
    String getIdentityType();


}
