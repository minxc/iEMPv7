package org.minxc.emp.system.core.dao;

import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.system.core.model.SysScheduleJob;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

/**
 * 系统计划任务数据操作
 *
 * @author didi
 */
@MapperScan
public interface SysScheduleJobDao  extends BaseDao<String, SysScheduleJob> {


    /**
     * 选择性插入
     *
     * @param entity
     *          实体
     * @return
     */
    int insertSelective(SysScheduleJob entity);

    /**
     * 选择性更新
     *
     * @param entity
     *          实体
     * @return
     */
    int updateByPrimaryKeySelective(SysScheduleJob entity);

    /**
     * 是否存在
     *
     * @param jobName
     *          工作名称
     * @param jobGroup
     *          工作分组
     * @return
     */
    boolean exists(@Param("jobName")String jobName, @Param("jobGroup")String jobGroup);
}
