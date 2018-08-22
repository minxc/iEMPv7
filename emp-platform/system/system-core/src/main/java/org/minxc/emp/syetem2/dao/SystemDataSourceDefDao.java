package org.minxc.emp.syetem2.dao;

import org.minxc.emp.syetem2.model.SystemDataSourceDef;
import org.mybatis.spring.annotation.MapperScan;

import org.minxc.emp.base.dao.BaseDao;

/**
 * <pre>
 * 描述：数据源模板 DAO接口
 * 构建组：白日梦团体
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018-01-03 18:04:15
 * 版权：summer
 * </pre>
 */
@MapperScan
public interface SystemDataSourceDefDao extends BaseDao<String, SystemDataSourceDef> {
}
