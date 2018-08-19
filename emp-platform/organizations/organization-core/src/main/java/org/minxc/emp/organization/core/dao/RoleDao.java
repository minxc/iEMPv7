package org.minxc.emp.organization.core.dao;

import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.organization.core.model.RoleModelImpl;

import java.util.List;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <pre>
 * 描述：角色管理 DAO接口
 * </pre>
 */
@MapperScan
public interface RoleDao extends BaseDao<String, RoleModelImpl> {
    RoleModelImpl getByAlias(String alias);

    List<RoleModelImpl> getList(@Param("userId") String userId, @Param("account") String account);

    /**
     * 判断角色系统中是否存在。
     *
     * @param role
     * @return
     */
    Integer isRoleExist(RoleModelImpl role);
}
