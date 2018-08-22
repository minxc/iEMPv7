package org.minxc.emp.organization.core.model;

import java.util.Date;
import java.util.Map;

import org.minxc.emp.base.core.model.AbstractCommonModel;
import org.minxc.emp.organization.api.model.IdentityType;
import org.minxc.emp.organization.api.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @version V1.0
 * @Title: UserEntity
 * @Package: org.minxc.emp.organization.core.model
 * @Description: 用户实体类
 * @author: Xianchang.min
 * @date 2018/8/22 20:00
 */
@Getter
@Setter
@ToString(callSuper=true)
@AllArgsConstructor
@Builder
public class UserEntity extends AbstractCommonModel implements User{
	
    /** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -3148602064505443394L;

    private String fullname;

    private String account;

    private String password;

    private String email;

    private String mobile;

    private String weixin;

    private String address;

    private String photo;

    private String sex;
    
    /*
     * 来源
     */
    @Builder.Default
    private String userfrom = "system";
    
    /**
     * 0:禁用，1正常
     */
    private Integer status;

    /**
     * 组织ID，用于在组织下添加用户。
     */
    @Builder.Default
    protected String groupId = "";


	@Override
	public String getIdentityType() {
		
		return IdentityType.USER;
	}

	@Override
	public String getUserId() {
		return id;
	}

	@Override
	public void setUserId(String userId) {
		this.id = userId;
	}

	@Override
	public void setAttributes(Map<String, String> map) {
		
	}

	@Override
	public Map<String, String> getAttributes() {
		return null;
	}

	@Override
	public String getAttrbuite(String key) {
		return null;
	}

}
