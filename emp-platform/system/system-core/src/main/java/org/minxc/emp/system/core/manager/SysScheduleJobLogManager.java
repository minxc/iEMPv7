package org.minxc.emp.system.core.manager;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.system.core.model.SysScheduleJobLog;

/**
 *
 * @author didi
 */
public interface SysScheduleJobLogManager extends Manager<String, SysScheduleJobLog> {

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


}
