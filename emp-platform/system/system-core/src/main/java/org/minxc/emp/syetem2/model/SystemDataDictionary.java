package org.minxc.emp.syetem2.model;

import org.minxc.emp.base.api.model.Tree;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 描述：系统
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年3月13日 下午5:36:18
 * 版权:summer
 * </pre>
 */
public class SystemDataDictionary implements Tree<SystemDataDictionary>, Serializable {
    /**
     * 主键
     */
    @NotEmpty
    private String id;
    /**
     * 数据源的别名
     */
    @NotEmpty
    private String key;
    /**
     * 数据源的名字
     */
    @NotEmpty
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 父ID
     */
    private String parentId;
    /**
     * 路径 eg:pppid.ppid.pid
     */
    private String path;
    /**
     * 排序号
     */
    private int sn;
    /**
     * 是否系统内置字典
     */
    private boolean system;
    /**
     * 是否显示在
     */
    private boolean show;

    // 以下字段与数据库无关
    private List<SystemDataDictionary> children;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public List<SystemDataDictionary> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<SystemDataDictionary> list) {
        children = list;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
