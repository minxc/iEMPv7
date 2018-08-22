package org.minxc.emp.system.core.dao;

import org.minxc.emp.model.SystemPropertiesEntity;

public interface SystemPropertiesEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemPropertiesEntity record);

    int insertSelective(SystemPropertiesEntity record);

    SystemPropertiesEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemPropertiesEntity record);

    int updateByPrimaryKey(SystemPropertiesEntity record);
}