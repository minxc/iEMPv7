package org.minxc.emp.system.core.dao;

import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.system.core.model.WorkbenchLayout;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface WorkbenchLayoutDao extends BaseDao<String, WorkbenchLayout> {

    void removeByUserId(String currentUserId);

    List<WorkbenchLayout> getByUserId(String userId);
}
