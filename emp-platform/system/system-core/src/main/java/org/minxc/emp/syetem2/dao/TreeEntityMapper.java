package org.minxc.emp.syetem2.dao;

import org.minxc.emp.syetem2.model.TreeEntity;

/**
 * 
* 项目名称：system-core   
* 类名称：TreeEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:34:35   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:34:35   
* 修改备注：   
* @version  1.0  
*
 */
public interface TreeEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(TreeEntity record);

    int insertSelective(TreeEntity record);

    TreeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TreeEntity record);

    int updateByPrimaryKey(TreeEntity record);
}