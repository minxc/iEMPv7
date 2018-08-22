package org.minxc.emp.organization.core.dao;

import org.minxc.emp.organization.core.model.UserRoleLinkEntity;

/**
 * 
* 项目名称：organization-core   
* 类名称：UserRoleLinkEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:33:29   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:33:29   
* 修改备注：   
* @version  1.0  
*
 */
public interface UserRoleLinkEntityMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(UserRoleLinkEntity record);

    int insertSelective(UserRoleLinkEntity record);

    UserRoleLinkEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRoleLinkEntity record);

    int updateByPrimaryKey(UserRoleLinkEntity record);
}