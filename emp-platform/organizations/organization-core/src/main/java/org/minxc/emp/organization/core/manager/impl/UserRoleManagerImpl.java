package org.minxc.emp.organization.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.base.db.model.query.DefaultQueryFilter;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.organization.core.dao.UserRoleDao;
import org.minxc.emp.organization.core.manager.UserRoleManager;
import org.minxc.emp.organization.core.model.UserRoleModelImpl;

/**
 * <pre>
 * 描述：用户角色管理 处理实现类
 * </pre>
 */
@Service
public class UserRoleManagerImpl extends BaseManager<String, UserRoleModelImpl> implements UserRoleManager {
    @Resource
    UserRoleDao userRoleDao;

    public UserRoleModelImpl getByRoleIdUserId(String roleId, String userId) {
        return userRoleDao.getByRoleIdUserId(roleId, userId);
    }

    // 这是什么鬼写法
    public List<UserRoleModelImpl> getListByUserId(String userId) {
    	return userRoleDao.queryByParams(null, userId, null);
    }

    public List<UserRoleModelImpl> getListByRoleId(String roleId) {
    	return userRoleDao.queryByParams(roleId, null, null);
    }

    public List<UserRoleModelImpl> getListByAlias(String alias) {
    	
    	return userRoleDao.queryByParams(null,null,alias);
    }
}
