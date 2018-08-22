package org.minxc.emp.system.core.dao;

import org.minxc.emp.system.core.model.SystemPropertiesEntity;

/*
 * 
* 项目名称：system-core   
* 类名称：SystemPropertiesEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:35:39   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:35:39   
* 修改备注：   
* @version  1.0  
*
 */
public interface SystemPropertiesEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemPropertiesEntity record);

    int insertSelective(SystemPropertiesEntity record);

    SystemPropertiesEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemPropertiesEntity record);

    int updateByPrimaryKey(SystemPropertiesEntity record);
}