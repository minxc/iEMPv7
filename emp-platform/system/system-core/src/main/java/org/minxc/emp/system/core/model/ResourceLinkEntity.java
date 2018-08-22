package org.minxc.emp.system.core.model;
/**  

* @Title: ResourceLinkEntity 

* @Package: org.minxc.emp.system.core.model 

* @Description:  关联资源实体对象

* @author: Xianchang.min  

* @date  2018/8/22 21:09 

* @version V1.0  

*/

public class ResourceLinkEntity {
    private String id;

    private String resId;

    private String name;

    private String resUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl == null ? null : resUrl.trim();
    }
}