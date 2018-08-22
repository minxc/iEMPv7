package org.minxc.emp.syetem2.dao;

import org.minxc.emp.syetem2.model.TreeNodeEntity;

/*
 * 
* 项目名称：system-core   
* 类名称：TreeNodeEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:34:47   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:34:47   
* 修改备注：   
* @version  1.0  
*
 */
public interface TreeNodeEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(TreeNodeEntity record);

    int insertSelective(TreeNodeEntity record);

    TreeNodeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TreeNodeEntity record);

    int updateByPrimaryKey(TreeNodeEntity record);
}