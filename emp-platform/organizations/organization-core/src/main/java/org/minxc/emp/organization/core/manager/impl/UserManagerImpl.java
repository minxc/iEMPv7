package org.minxc.emp.organization.core.manager.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.organization.core.dao.UserDao;
import org.minxc.emp.organization.core.dao.UserRoleDao;
import org.minxc.emp.organization.core.manager.UserManager;
import org.minxc.emp.organization.core.model.UserModelImpl;

/**
 * <pre>
 * 描述：用户表 处理实现类
 * </pre>
 */
@Service
public class UserManagerImpl extends BaseManager<String, UserModelImpl> implements UserManager {
    @Resource
    private UserDao userDao;
    @Resource
    private UserRoleDao userRoleDao;

    public UserModelImpl getByAccount(String account) {
        return this.userDao.getByAccount(account);
    }

    /**
     * 不含组织用户关系数据
     */
    public List<UserModelImpl> queryOrgUser(QueryFilter queryFilter) {
        return userDao.queryOrgUser(queryFilter);
    }

    /**
     * 不含组织用户关系数据
     */
    public List<UserModelImpl> getUserListByOrgId(String orgId) {
    	return userDao.getUserListByOrgId(orgId);
    }


    public List<UserModelImpl> getListByRelCode(String relCode) {
        return userDao.getListByRelCode(relCode);
    }

    public List<UserModelImpl> getListByRelId(String relId) {
        return userDao.getUserListByRelId(relId);
    }

    public List<UserModelImpl> getUserListByRoleId(String roleId) {
    	if(StringUtil.isEmpty(roleId))return Collections.emptyList();
    	
        return userDao.getUserListByRole(roleId, null);
    }

    @Override
    public boolean isUserExist(UserModelImpl user) {
        return userDao.isUserExist(user)>0;
    }

    @Override
    public List queryUserGroupRel(QueryFilter queryFilter) {
        return (List) userDao.queryUserGroupRel(queryFilter);
    }

	@Override
	public List<UserModelImpl> getUserListByRoleCode(String roleCode) {
		if(StringUtil.isEmpty(roleCode))return Collections.emptyList();
		
		return userDao.getUserListByRole(null, roleCode);
	}
}
