package org.minxc.emp.syetem2.dao;

import org.minxc.emp.model.TreeNodeEntity;

public interface TreeNodeEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(TreeNodeEntity record);

    int insertSelective(TreeNodeEntity record);

    TreeNodeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TreeNodeEntity record);

    int updateByPrimaryKey(TreeNodeEntity record);
}