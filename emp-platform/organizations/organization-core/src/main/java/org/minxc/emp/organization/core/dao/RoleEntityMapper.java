package org.minxc.emp.organization.core.dao;

import org.minxc.emp.organization.core.model.RoleEntity;

/**
 * 
* 项目名称：organization-core   
* 类名称：RoleEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:33:08   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:33:08   
* 修改备注：   
* @version  1.0  
*
 */
public interface RoleEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleEntity record);

    int insertSelective(RoleEntity record);

    RoleEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleEntity record);

    int updateByPrimaryKey(RoleEntity record);
}