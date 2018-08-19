package org.minxc.emp.organization.core.manager;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.organization.core.model.GroupRelDef;

/**
 * <pre>
 * 描述：组织关系定义 处理接口
 * </pre>
 */
public interface GroupRelDefManager extends Manager<String, GroupRelDef> {
    /**
     * 根据职务编码获取职务定义
     *
     * @param code
     * @return
     */
    public GroupRelDef getByCode(String code);

}
