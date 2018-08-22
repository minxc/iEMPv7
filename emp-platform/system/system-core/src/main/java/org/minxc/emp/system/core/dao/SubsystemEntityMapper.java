package org.minxc.emp.system.core.dao;

import org.minxc.emp.system.core.model.SubsystemEntity;

/**
 * 
* 项目名称：system-core   
* 类名称：SubsystemEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:35:18   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:35:18   
* 修改备注：   
* @version  1.0  
*
 */
public interface SubsystemEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubsystemEntity record);

    int insertSelective(SubsystemEntity record);

    SubsystemEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubsystemEntity record);

    int updateByPrimaryKey(SubsystemEntity record);
}