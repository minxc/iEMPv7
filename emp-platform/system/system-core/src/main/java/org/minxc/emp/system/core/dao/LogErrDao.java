package org.minxc.emp.system.core.dao;

import org.minxc.emp.system.core.model.LogErr;
import org.mybatis.spring.annotation.MapperScan;

import org.minxc.emp.base.dao.BaseDao;

/**
 * <pre>
 * 描述：错误日志 DAO接口
 * </pre>
 */
@MapperScan
public interface LogErrDao extends BaseDao<String, LogErr> {
}
