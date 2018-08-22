package org.minxc.emp.organization.core.dao;

import org.minxc.emp.organization.core.model.PositionEntity;

public interface PositionEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(PositionEntity record);

    int insertSelective(PositionEntity record);

    PositionEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PositionEntity record);

    int updateByPrimaryKey(PositionEntity record);
}