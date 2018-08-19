package org.minxc.emp.organization.core.manager;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.organization.core.model.GroupModelImpl;

import java.util.List;

/**
 * <pre>
 * 描述：组织架构 处理接口
 * </pre>
 */
public interface GroupManager extends Manager<String, GroupModelImpl> {
    /**
     * 根据Code取定义对象。
     *
     * @param code
     * @return
     */
    GroupModelImpl getByCode(String code);

    /**
     * 根据用户ID获取组织列表
     *
     * @param userId
     * @return
     */
    List<GroupModelImpl> getByUserId(String userId);

    /**
     * 获取主组织。
     *
     * @param userId
     * @return
     */
    GroupModelImpl getMainGroup(String userId);
}
