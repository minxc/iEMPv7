package org.minxc.emp.syetem2.dao;

import org.minxc.emp.syetem2.model.SystemTree;
import org.mybatis.spring.annotation.MapperScan;

import org.minxc.emp.base.dao.BaseDao;

/**
 * 系统树 DAO接口
 *
 * @author aschs
 * @email aschs@qq.com
 * @time 2018-03-13 19:58:28
 */
@MapperScan
public interface SystemTreeDao extends BaseDao<String, SystemTree> {

}
