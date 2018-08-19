package org.minxc.emp.system.core.manager.impl;

import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.system.core.dao.SysScheduleJobLogDao;
import org.minxc.emp.system.core.manager.SysScheduleJobLogManager;
import org.minxc.emp.system.core.model.SysScheduleJobLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author didi
 */
@Service("sysScheduleJobLogManager")
public class SysScheduleJobLogManagerImpl extends BaseManager<String, SysScheduleJobLog> implements SysScheduleJobLogManager {

    @Resource
    private SysScheduleJobLogDao sysScheduleJobLogDao;

    @Override
    public int insertSelective(SysScheduleJobLog entity) {

        return sysScheduleJobLogDao.insertSelective(entity);
    }

    @Override
    public int updateByPrimaryKeySelective(SysScheduleJobLog entity) {

        return sysScheduleJobLogDao.updateByPrimaryKeySelective(entity);
    }
}
