package org.minxc.emp.syetem2.dao;

import org.minxc.emp.syetem2.model.DataSourceEntity;

/**
 * 
* 项目名称：system-core   
* 类名称：DataSourceEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:34:30   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:34:30   
* 修改备注：   
* @version  1.0  
*
 */
public interface DataSourceEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(DataSourceEntity record);

    int insertSelective(DataSourceEntity record);

    DataSourceEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DataSourceEntity record);

    int updateByPrimaryKeyWithBLOBs(DataSourceEntity record);

    int updateByPrimaryKey(DataSourceEntity record);
}