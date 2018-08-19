package org.minxc.emp.system.core.dao;

import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.system.core.model.SystemProperties;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

/**
 * <pre>
 * 描述：SYS_PROPERTIES DAO接口
 * </pre>
 */
@MapperScan
public interface SysPropertiesDao extends BaseDao<String, SystemProperties> {

    /**
     * 分组列表。
     *
     * @return
     */
    List<String> getGroups();


    /**
     * 判断别名是否存在。
     *
     * @param sysProperties
     * @return
     */
    Integer isExist(SystemProperties sysProperties);
}
