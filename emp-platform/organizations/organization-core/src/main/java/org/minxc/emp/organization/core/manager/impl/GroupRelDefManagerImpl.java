package org.minxc.emp.organization.core.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.organization.core.dao.GroupRelDefDao;
import org.minxc.emp.organization.core.manager.GroupRelDefManager;
import org.minxc.emp.organization.core.model.GroupRelDef;

/**
 * <pre>
 * 描述：组织关系定义 处理实现类
 * </pre>
 */
@Service
public class GroupRelDefManagerImpl extends BaseManager<String, GroupRelDef> implements GroupRelDefManager {
    @Resource
    GroupRelDefDao groupRelDefDao;


    public GroupRelDef getByCode(String code) {
        return groupRelDefDao.getByCode(code);
    }

}
