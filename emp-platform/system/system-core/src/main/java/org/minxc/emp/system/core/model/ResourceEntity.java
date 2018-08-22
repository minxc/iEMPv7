package org.minxc.emp.system.core.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @version V1.0
 * @Title: ResourceEntity
 * @Package: org.minxc.emp.system.core.model
 * @Description: 系统资源实体类
 * @author: Xianchang.min
 * @date 2018/8/22 20:51
 */

public class ResourceEntity {
    private String id;

    private String systemId;

    private String alias;

    private String name;

    private String defaultUrl;

    private Long enableMenu;

    private Long hasChildren;

    private Long opened;

    private String icon;

    private Long newWindow;

    private BigDecimal seq;

    private String parentId;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl == null ? null : defaultUrl.trim();
    }

    public Long getEnableMenu() {
        return enableMenu;
    }

    public void setEnableMenu(Long enableMenu) {
        this.enableMenu = enableMenu;
    }

    public Long getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Long hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Long getOpened() {
        return opened;
    }

    public void setOpened(Long opened) {
        this.opened = opened;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Long getNewWindow() {
        return newWindow;
    }

    public void setNewWindow(Long newWindow) {
        this.newWindow = newWindow;
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
