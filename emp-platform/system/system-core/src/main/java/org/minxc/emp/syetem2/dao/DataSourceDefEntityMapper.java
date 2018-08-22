package org.minxc.emp.syetem2.dao;

import org.minxc.emp.syetem2.model.DataSourceDefEntity;

/**
 * 
* 项目名称：system-core   
* 类名称：DataSourceDefEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:34:18   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:34:18   
* 修改备注：   
* @version  1.0  
*
 */
public interface DataSourceDefEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(DataSourceDefEntity record);

    int insertSelective(DataSourceDefEntity record);

    DataSourceDefEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DataSourceDefEntity record);

    int updateByPrimaryKeyWithBLOBs(DataSourceDefEntity record);

    int updateByPrimaryKey(DataSourceDefEntity record);
}