package org.minxc.emp.organization.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.organization.core.dao.GroupDao;
import org.minxc.emp.organization.core.dao.UserDao;
import org.minxc.emp.organization.core.manager.GroupManager;
import org.minxc.emp.organization.core.model.GroupModelImpl;
import org.minxc.emp.organization.core.model.UserModelImpl;

/**
 *
 * 描述：组织架构 处理实现类
 *
 */
@Service
public class GroupManagerImpl extends BaseManager<String, GroupModelImpl> implements GroupManager {
    @Resource
    private GroupDao groupDao;
    @Resource
    private UserDao userDao;


    public GroupModelImpl getByCode(String code) {
        return groupDao.getByCode(code);
    }

    public List<GroupModelImpl> getByUserId(String userId) {
        return groupDao.getByUserId(userId);
    }

    public List<GroupModelImpl> getByUserAccount(String account) {
        UserModelImpl user = userDao.getByAccount(account);
        return groupDao.getByUserId(user.getId());
    }

    @Override
    public GroupModelImpl getMainGroup(String userId) {
        List<GroupModelImpl> list = groupDao.getByUserId(userId);
        if (BeanUtils.isEmpty(list)) return null;
        if (list.size() == 1) return list.get(0);
        for (GroupModelImpl org : list) {
            if (org.getIsMaster() == 1) return org;
        }
        return list.get(0);
    }
}
