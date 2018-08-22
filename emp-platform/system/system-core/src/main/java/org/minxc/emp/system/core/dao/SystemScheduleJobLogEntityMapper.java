package org.minxc.emp.system.core.dao;

import org.minxc.emp.system.core.model.SystemScheduleJobLogEntity;

/**
 * 
* 项目名称：system-core   
* 类名称：SystemScheduleJobLogEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:36:01   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:36:01   
* 修改备注：   
* @version  1.0  
*
 */
public interface SystemScheduleJobLogEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemScheduleJobLogEntity record);

    int insertSelective(SystemScheduleJobLogEntity record);

    SystemScheduleJobLogEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemScheduleJobLogEntity record);

    int updateByPrimaryKeyWithBLOBs(SystemScheduleJobLogEntity record);

    int updateByPrimaryKey(SystemScheduleJobLogEntity record);
}