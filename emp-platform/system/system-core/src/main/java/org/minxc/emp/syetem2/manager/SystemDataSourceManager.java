package org.minxc.emp.syetem2.manager;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.syetem2.model.SystemDataSourceImpl;

/**
 * <pre>
 * 描述：数据源 Manager处理接口
 * 构建组：白日梦团体
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018-01-08 21:14:05
 * 版权：summer
 * </pre>
 */
public interface SystemDataSourceManager extends Manager<String, SystemDataSourceImpl> {

    /**
     * <pre>
     * 把sysDataSource转换成javax.sql.DataSource
     * </pre>
     *
     * @param sysDataSource
     * @return
     */
    DataSource tranform2DataSource(SystemDataSourceImpl sysDataSource);

    /**
     * <pre>
     * 根据key获取对象
     * </pre>
     *
     * @param key
     * @return
     */
    SystemDataSourceImpl getByKey(String key);

    /**
     * <pre>
     * 根据数据库别名获取 javax.sql.DataSource
     * 1 先从spring的容器数据源（DynamicDataSource）中拿
     * 2 再从系统配置的数据源(sysDataSource)中拿
     * ps：所以，如果你在spring配置文件中配置的数据源别名跟系统配置的别名一样，会优先取spring配置文件中的
     * </pre>
     *
     * @param key
     * @param add 当数据源是从系统中取出来的，是否追加到spring容器数据源中
     * @return
     */
    DataSource getDataSourceByKey(String key, boolean add);

    /**
     * <pre>
     * 根据数据库别名获取 javax.sql.DataSource
     * 1 先从spring的容器数据源（DynamicDataSource）中拿
     * 2 再从系统配置的数据源(sysDataSource)中拿，当数据源是从系统中取出来的时，追加到spring容器数据源中
     * ps：所以，如果你在spring配置文件中配置的数据源别名跟系统配置的别名一样，会优先取spring配置文件中的
     * </pre>
     *
     * @param key
     * @return
     */
    DataSource getDataSourceByKey(String key);

    /**
     * <pre>
     * 根据数据库别名获取 javax.sql.JdbcTemplate
     * 1 先从spring的容器数据源（DynamicDataSource）中拿
     * 2 再从系统配置的数据源(sysDataSource)中拿，当数据源是从系统中取出来的时，追加到spring容器数据源中
     * 3 把拿到的数据源new一个JdbcTemplate
     * ps：所以，如果你在spring配置文件中配置的数据源别名跟系统配置的别名一样，会优先取spring配置文件中的
     * </pre>
     *
     * @param key
     * @return
     */
    JdbcTemplate getJdbcTemplateByKey(String key);

}
