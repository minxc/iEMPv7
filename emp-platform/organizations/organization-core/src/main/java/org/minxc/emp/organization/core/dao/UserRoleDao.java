package org.minxc.emp.organization.core.dao;

import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.organization.core.model.UserRoleModelImpl;

import java.util.List;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <pre>
 * 描述：用户角色管理 DAO接口
 * </pre>
 */
@MapperScan
public interface UserRoleDao extends BaseDao<String, UserRoleModelImpl> {

    /**
     * 根据用户和角色id 查询 关联关系。
     *
     * @param roleId
     * @param userId
     * @return
     */
    UserRoleModelImpl getByRoleIdUserId(@Param("roleId")String roleId, @Param("userId") String userId);

	List<UserRoleModelImpl> queryByParams(@Param("roleId")String roleId, @Param("userId")String userId, @Param("alias") String alias);
}
