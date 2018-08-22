package org.minxc.emp.organization.core.model;

/**
 * @version V1.0
 * @Title: GroupPositionLinkEntity
 * @Package: org.minxc.emp.organization.core.model
 * @Description: 组织岗位关联信息表
 * @author: Xianchang.min
 * @date 2018/8/22 19:50
 */

public class GroupPositionLinkEntity {
    private String id;

    private String groupId;

    private String positionId;

    private String positionName;

    private String positionCode;

    private String jobName;

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

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId == null ? null : positionId.trim();
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }
}
