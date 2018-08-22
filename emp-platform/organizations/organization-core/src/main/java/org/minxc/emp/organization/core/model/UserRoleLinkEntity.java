package org.minxc.emp.organization.core.model;

/**
 * @version V1.0
 * @Title: UserRoleLinkEntity
 * @Package: org.minxc.emp.organization.core.model
 * @Description: 用户角色关联表
 * @author: Xianchang.min
 * @date 2018/8/22 20:01
 */

public class UserRoleLinkEntity {

    private String id;

    private String roleId;

    private String userId;

    private Long enabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }
}
