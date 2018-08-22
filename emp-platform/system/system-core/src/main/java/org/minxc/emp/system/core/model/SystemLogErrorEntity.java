package org.minxc.emp.system.core.model;

import java.util.Date;

/**
 * @version V1.0
 * @Title: SystemLogErrorEntity
 * @Package: org.minxc.emp.system.core.model
 * @Description: 系统错误日志实体对象
 * @author: Xianchang.min
 * @date 2018/8/22 20:46
 */

public class SystemLogErrorEntity {
    private String id;

    private String account;

    private String ip;

    private String url;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
