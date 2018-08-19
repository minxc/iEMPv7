package org.minxc.emp.organization.core.manager;

import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.organization.core.model.UserModelImpl;

import java.util.List;

/**
 * <pre>
 * 描述：用户表 处理接口
 * </pre>
 */
public interface UserManager extends Manager<String, UserModelImpl> {
    /**
     * 根据Account取定义对象。
     *
     * @param code
     * @return
     */
    UserModelImpl getByAccount(String account);

    /**
     * 不含用户组织关系
     */
    List<UserModelImpl> getUserListByOrgId(String orgId);

    /**
     * 不含用户组织关系
     *
     * @param queryFilter
     * @return
     */
    List<UserModelImpl> queryOrgUser(QueryFilter queryFilter);

    /**
     * 含组织用户关系表数据
     *
     * @param queryFilter
     * @return
     */
    List queryUserGroupRel(QueryFilter queryFilter);

    /**
     * 根据岗位编码获取用户列表
     *
     * @param relCode
     * @return
     */
    List<UserModelImpl> getListByRelCode(String relCode);

    /**
     * 根据岗位ID获取用户列表
     *
     * @param relCode
     * @return
     */
    List<UserModelImpl> getListByRelId(String relId);

    /**
     * 根据角色ID获取用户列表
     *
     * @param roleId
     * @return
     */
    List<UserModelImpl> getUserListByRoleId(String roleId);

    /**
     * 根据角色Code获取用户列表
     *
     * @param roleId
     * @return
     */
    List<UserModelImpl> getUserListByRoleCode(String roleCode);

    /**
     * 判断系统中用户是否存在。
     *
     * @param user
     * @return
     */
    boolean isUserExist(UserModelImpl user);
}
