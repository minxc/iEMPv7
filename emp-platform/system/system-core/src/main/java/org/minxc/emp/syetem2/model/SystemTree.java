package org.minxc.emp.syetem2.model;

import org.minxc.emp.base.core.model.BaseModelImpl;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <pre>
 * 描述：系统树
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:2018年3月13日 下午6:43:24
 * 版权:summer
 * </pre>
 */
public class SystemTree extends BaseModelImpl {
    /**
     * 别名
     */
    @NotEmpty
    private String key;
    /**
     * 名字
     */
    @NotEmpty
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 是否系统内置树
     */
    private boolean system;

    // 以下字段与数据库无关
    /**
     * 树的顶部节点
     */
    private List<SystemTreeNodeImpl> nodes;

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

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public List<SystemTreeNodeImpl> getNodes() {
        return nodes;
    }

    public void setNodes(List<SystemTreeNodeImpl> nodes) {
        this.nodes = nodes;
    }

}
