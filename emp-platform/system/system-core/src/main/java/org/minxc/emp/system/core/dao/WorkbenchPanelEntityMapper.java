package org.minxc.emp.system.core.dao;


import org.minxc.emp.system.core.model.WorkbenchPanelEntity;

public interface WorkbenchPanelEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(WorkbenchPanelEntity record);

    int insertSelective(WorkbenchPanelEntity record);

    WorkbenchPanelEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkbenchPanelEntity record);

    int updateByPrimaryKeyWithBLOBs(WorkbenchPanelEntity record);

    int updateByPrimaryKey(WorkbenchPanelEntity record);
}