package org.minxc.emp.organization.core.model;

/**
 * @version V1.0
 * @Title: PositionEntity
 * @Package: org.minxc.emp.organization.core.model
 * @Description: 岗位信息表
 * @author: Xianchang.min
 * @date 2018/8/22 19:53
 */

public class PositionEntity {

    private String id;

    private String name;

    private String code;

    private String postLevel;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPostLevel() {
        return postLevel;
    }

    public void setPostLevel(String postLevel) {
        this.postLevel = postLevel == null ? null : postLevel.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}
