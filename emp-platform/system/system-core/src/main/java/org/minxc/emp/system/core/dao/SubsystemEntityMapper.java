package org.minxc.emp.system.core.dao;

import org.minxc.emp.model.SubsystemEntity;

public interface SubsystemEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubsystemEntity record);

    int insertSelective(SubsystemEntity record);

    SubsystemEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubsystemEntity record);

    int updateByPrimaryKey(SubsystemEntity record);
}