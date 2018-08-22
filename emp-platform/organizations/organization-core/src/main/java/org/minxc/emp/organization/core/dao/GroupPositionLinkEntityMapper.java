package org.minxc.emp.organization.core.dao;


import org.minxc.emp.organization.core.model.GroupPositionLinkEntity;

public interface GroupPositionLinkEntityMapper {

    int deleteByPrimaryKey(String id);

    int insert(GroupPositionLinkEntity record);

    int insertSelective(GroupPositionLinkEntity record);

    GroupPositionLinkEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupPositionLinkEntity record);

    int updateByPrimaryKey(GroupPositionLinkEntity record);
}