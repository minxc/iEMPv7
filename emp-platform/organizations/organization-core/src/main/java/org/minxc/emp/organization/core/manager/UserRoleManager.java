package org.minxc.emp.organization.core.manager;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.organization.core.model.UserRoleModelImpl;

import java.util.List;

/**
 * <pre>
 * 描述：用户角色管理 处理接口
 * </pre>
 */
public interface UserRoleManager extends Manager<String, UserRoleModelImpl> {

    /**
     * 根据用户和角色id 查询 关联关系。
     *
     * @param roleId
     * @param userId
     * @return
     */
    UserRoleModelImpl getByRoleIdUserId(String roleId, String userId);

    /**
     * 获取用户的角色。
     *
     * @param userId
     * @return
     */
    List<UserRoleModelImpl> getListByUserId(String userId);

    /**
     * 根据角色ID查询关联的用户。
     *
     * @param roleId
     * @return
     */
    List<UserRoleModelImpl> getListByRoleId(String roleId);

   /*
    * @Title: getListByAlias
    
    * @Description: 根据角色别名查询关联的用户。
    
    * @param [alias]   参数类型
    
    * @return java.util.List<org.minxc.emp.organization.core.model.UserRoleModelImpl>    返回类型 
    
    * @throws     抛出异常
    
    **/ 
    List<UserRoleModelImpl> getListByAlias(String alias);
}
