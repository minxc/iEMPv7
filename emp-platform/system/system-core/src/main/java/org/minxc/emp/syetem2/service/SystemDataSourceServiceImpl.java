package org.minxc.emp.syetem2.service;

import org.minxc.emp.system.api2.model.SystemDataSource;
import org.minxc.emp.system.api2.service.SystemDataSourceService;
import org.minxc.emp.syetem2.manager.SystemDataSourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * <pre>
 * 描述：系统数据源对其他模块的服务类
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:2018年1月29日 上午10:42:46
 * 版权:summer
 * </pre>
 */
@Service
public class SystemDataSourceServiceImpl implements SystemDataSourceService {
    @Autowired
    SystemDataSourceManager sysDataSourceManager;

    @Override
    public SystemDataSource getByKey(String key) {
        return sysDataSourceManager.getByKey(key);
    }

    @Override
    public DataSource getDataSourceByKey(String key) {
        return sysDataSourceManager.getDataSourceByKey(key);
    }

    @Override
    public JdbcTemplate getJdbcTemplateByKey(String key) {
        return sysDataSourceManager.getJdbcTemplateByKey(key);
    }
}
