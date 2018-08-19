package org.minxc.emp.system.core.manager.impl;

import javax.annotation.Resource;

import org.minxc.emp.system.core.dao.SysScheduleJobDao;
import org.springframework.stereotype.Service;

import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.system.core.manager.SysScheduleJobManager;
import org.minxc.emp.system.core.model.SysScheduleJob;

/**
 * 系统执行计划通过处理
 *
 * @author didi
 */
@Service("sysScheduleJobManager")
public class SysScheduleJobManagerImpl extends BaseManager<String, SysScheduleJob> implements SysScheduleJobManager {

    @Resource(name = "sysScheduleJobDao")
    private SysScheduleJobDao sysScheduleJobDao;
 
}
