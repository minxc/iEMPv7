package org.minxc.emp.organization.core.dao;

import org.minxc.emp.organization.core.model.UserEntity;

/**
 * 
* 项目名称：organization-core   
* 类名称：UserEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:33:19   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:33:19   
* 修改备注：   
* @version  1.0  
*
 */
public interface UserEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);
}