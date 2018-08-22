package org.minxc.emp.organization.core.model;

/**
 * @version V1.0
 * @Title: RoleEntity
 * @Package: org.minxc.emp.organization.core.model
 * @Description: 系统角色实体类
 * @author: Xianchang.min
 * @date 2018/8/22 19:58
 */

public class RoleEntity {

    private String id;

    private String name;

    private String alias;

    private Long enabled;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}
