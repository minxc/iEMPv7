package org.minxc.emp.system.core.dao;

import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.system.core.model.SysScheduleJobLog;

/**
 * 任务计划日志持久化
 *
 * @author didi
 */
public interface SysScheduleJobLogDao extends BaseDao<String, SysScheduleJobLog> {

    /**
     * 选择性插入
     * @param entity
     *          实体
     * @return
     */
    int insertSelective(SysScheduleJobLog entity);

    /**
     * 选择性更新
     *
     * @param entity
     *          更新
     * @return
     */
    int updateByPrimaryKeySelective(SysScheduleJobLog entity);

    /**
     * 根据任务计划ID移除
     * @param jobId
     * @return
     */
    int removeByJobId(String jobId);
}
