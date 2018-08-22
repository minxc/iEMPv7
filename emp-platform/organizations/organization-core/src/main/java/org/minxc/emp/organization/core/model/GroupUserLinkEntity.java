package org.minxc.emp.organization.core.model;

/**
 * @version V1.0
 * @Title: GroupUserLinkEntity
 * @Package: org.minxc.emp.organization.core.model
 * @Description: 组织用户关联信息表
 * @author: Xianchang.min
 * @date 2018/8/22 19:54
 */

public class GroupUserLinkEntity {

    private String id;

    private String groupId;

    private String userId;

    private Long isMaster;

    private String positionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(Long isMaster) {
        this.isMaster = isMaster;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId == null ? null : positionId.trim();
    }
}
