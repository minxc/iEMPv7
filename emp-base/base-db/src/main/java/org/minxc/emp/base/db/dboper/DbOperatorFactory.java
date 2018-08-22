package org.minxc.emp.base.db.dboper;

import org.minxc.emp.base.core.util.AppUtil;
import org.minxc.emp.base.core.util.PropertyUtil;
import org.minxc.emp.base.db.api.table.DbType;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * <pre>
 * 描述：DbOperator的工厂类
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年1月22日 下午8:37:04
 * 版权:summer
 * </pre>
 */
public class DbOperatorFactory {
    private DbOperatorFactory() {

    }

    /**
     * <pre>
     * 构建一个操作器
     * </pre>
     *
     * @param type
     * @param jdbcTemplate
     * @return
     */
    public static DbOperator newOperator(String type, JdbcTemplate jdbcTemplate) {
        if (DbType.MYSQL.equalsWithKey(type)) {
            return new MysqlDbOperator(jdbcTemplate);
        }
        return null;
    }
    
    /**
     * <pre>
     * 获取本地数据库操作类
     * </pre>	
     * @return
     */
    public static DbOperator getLocal() {
    	return newOperator(PropertyUtil.getJdbcType(), AppUtil.getBean(JdbcTemplate.class));
    }
}
