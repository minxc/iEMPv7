package org.minxc.emp.organization.core.dao;

import org.minxc.emp.organization.core.model.GroupUserLinkEntity;

/**
 * 
* 项目名称：organization-core   
* 类名称：GroupUserLinkEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:32:43   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:32:43   
* 修改备注：   
* @version  1.0  
*
 */
public interface GroupUserLinkEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupUserLinkEntity record);

    int insertSelective(GroupUserLinkEntity record);

    GroupUserLinkEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupUserLinkEntity record);

    int updateByPrimaryKey(GroupUserLinkEntity record);
}