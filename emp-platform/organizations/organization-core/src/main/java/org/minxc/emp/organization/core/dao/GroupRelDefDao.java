package org.minxc.emp.organization.core.dao;

import org.minxc.emp.organization.core.model.GroupRelDef;
import org.mybatis.spring.annotation.MapperScan;

import org.minxc.emp.base.dao.BaseDao;

/**
 * <pre>
 * 描述：组织关系定义 DAO接口
 * </pre>
 */
@MapperScan
public interface GroupRelDefDao extends BaseDao<String, GroupRelDef> {
    public GroupRelDef getByCode(String code);
}
