package org.minxc.emp.organization.core.manager.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.organization.core.dao.RoleDao;
import org.minxc.emp.organization.core.manager.RoleManager;
import org.minxc.emp.organization.core.model.RoleModelImpl;

/**
 * <pre>
 * 描述：角色管理 处理实现类
 * </pre>
 */
@Service
public class RoleManagerImpl extends BaseManager<String, RoleModelImpl> implements RoleManager {
    @Resource
    RoleDao roleDao;

    public RoleModelImpl getByAlias(String alias) {
        return roleDao.getByAlias(alias);
    }

    public List<RoleModelImpl> getListByUserId(String userId) {
    	if(StringUtil.isEmpty(userId))return Collections.emptyList();
        return roleDao.getList(userId, null);
    }

    public List<RoleModelImpl> getListByAccount(String account) {
    	if(StringUtil.isEmpty(account))return Collections.emptyList();
    	
        return roleDao.getList(null,account);
    }

    @Override
    public boolean isRoleExist(RoleModelImpl role) {
        return roleDao.isRoleExist(role) != 0;
    }

}
