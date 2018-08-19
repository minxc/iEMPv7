package org.minxc.emp.system.core.dao;

import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.system.core.model.WorkbenchPanel;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface WorkbenchPanelDao extends BaseDao<String, WorkbenchPanel> {
    /**
     * 获取用户可用的
     *
     * @param query
     * @return
     */
    List<WorkbenchPanel> getUsablePanelsByUserRight(QueryFilter query);

    /**
     * 通过权限过滤获取用户的panel
     *
     * @param userPermission
     * @return
     */
    List<WorkbenchPanel> getByUser(Map<String, Object> userPermission);


    List<WorkbenchPanel> getBylayoutKey(String layoutKey);

}
