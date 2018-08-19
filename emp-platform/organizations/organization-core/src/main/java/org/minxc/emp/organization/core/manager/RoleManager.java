package org.minxc.emp.organization.core.manager;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.organization.core.model.RoleModelImpl;

import java.util.List;

/**
 * <pre>
 * 描述：角色管理 处理接口
 * </pre>
 */
public interface RoleManager extends Manager<String, RoleModelImpl> {


    RoleModelImpl getByAlias(String alias);

    /**
     * 根据用户ID获取角色列表
     *
     * @param userId
     * @return
     */
    List<RoleModelImpl> getListByUserId(String userId);

    /**
     * 判断角色是否存在。
     *
     * @param role
     * @return
     */
    boolean isRoleExist(RoleModelImpl role);

}
