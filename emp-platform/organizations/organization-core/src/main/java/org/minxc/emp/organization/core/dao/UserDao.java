package org.minxc.emp.organization.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.organization.core.model.UserModelImpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * <pre>
 * 描述：用户表 DAO接口
 * </pre>
 */

@Mapper
public interface UserDao extends BaseDao<String, UserModelImpl> {
    /**
     * 根据Account取定义对象。
     *
     * @param account
     * @return
     */
    UserModelImpl getByAccount(String account);

    /**
     * 不含用户组织关系
     *
     * @param queryFilter
     * @return
     */
    List<UserModelImpl> queryOrgUser(QueryFilter queryFilter);

    /**
     * 根据岗位编码获取用户列表
     *
     * @param relCode
     * @return
     */
    List<UserModelImpl> getListByRelCode(String relCode);

    /**
     * 根据角色获取用户列表
     *
     * @param roleId
     * @return
     */
    List<UserModelImpl> getUserListByRole(@Param("roleId")String roleId, @Param("roleCode")String roleCode);

    /**
     * 判断用户是否存在。
     *
     * @param user
     * @return
     */
    Integer isUserExist(UserModelImpl user);

    List getUserListByRelId(String relId);

	Map queryUserGroupRel(QueryFilter queryFilter);

	List<UserModelImpl> getUserListByOrgId(String orgId);
}
