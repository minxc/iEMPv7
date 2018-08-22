package org.minxc.emp.system.core.dao;

import org.minxc.emp.system.core.model.WorkbenchLayoutEntity;

/**
 * 
* 项目名称：system-core   
* 类名称：WorkbenchLayoutEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:36:06   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:36:06   
* 修改备注：   
* @version  1.0  
*
 */
public interface WorkbenchLayoutEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(WorkbenchLayoutEntity record);

    int insertSelective(WorkbenchLayoutEntity record);

    WorkbenchLayoutEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkbenchLayoutEntity record);

    int updateByPrimaryKey(WorkbenchLayoutEntity record);
}