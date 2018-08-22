package org.minxc.emp.system.core.dao;

import org.minxc.emp.system.core.model.SystemScheduleJobEntity;

/**
 * 
* 项目名称：system-core   
* 类名称：SystemScheduleJobEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:35:50   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:35:50   
* 修改备注：   
* @version  1.0  
*
 */
public interface SystemScheduleJobEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemScheduleJobEntity record);

    int insertSelective(SystemScheduleJobEntity record);

    SystemScheduleJobEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemScheduleJobEntity record);

    int updateByPrimaryKey(SystemScheduleJobEntity record);
}