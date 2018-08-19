package org.minxc.emp.system.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.system.core.dao.SubsystemDao;
import org.springframework.stereotype.Service;

import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.organization.api.model.User;
import org.minxc.emp.system.core.manager.SubsystemManager;
import org.minxc.emp.system.core.model.SubsystemImpl;
import org.minxc.emp.system.util.ContextUtil;

/**
 * <pre>
 * 描述：子系统定义 处理实现类
 * </pre>
 */
@Service("subsystemManager")
public class SubsystemManagerImpl extends BaseManager<String, SubsystemImpl> implements SubsystemManager {
    @Resource
    SubsystemDao subsystemDao;

    @Override
    public boolean isExist(SubsystemImpl subsystem) {
        return subsystemDao.isExist(subsystem)>0;
    }

    @Override
    public List<SubsystemImpl> getList() {
        return subsystemDao.getList();
    }

    @Override
    public SubsystemImpl getDefaultSystem(String userId) {
        List<SubsystemImpl> list = subsystemDao.getSystemByUser(userId);
        if (BeanUtils.isEmpty(list)) return null;

        for (SubsystemImpl system : list) {
            if (1 == system.getIsDefault()) return system;
        }
        return list.get(0);
    }

    @Override
    public void setDefaultSystem(String systemId) {
        SubsystemImpl subSystem = subsystemDao.get(systemId);
        if (subSystem.getIsDefault() == 1) {
            subSystem.setIsDefault(0);
        } else {
            subsystemDao.updNoDefault();
            subSystem.setIsDefault(1);
        }
        subsystemDao.update(subSystem);
    }


    @Override
    public List<SubsystemImpl> getCuurentUserSystem() {
        User user = ContextUtil.getCurrentUser();
        if (ContextUtil.isAdmin(user)) {
            return subsystemDao.getList();
        }

        return subsystemDao.getSystemByUser(user.getUserId());
    }
}
