package org.minxc.emp.base.db.tableoper;

import org.minxc.emp.base.db.api.table.DbType;
import org.minxc.emp.base.db.model.table.Table;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * TableOperator的工厂类
 *
 * @author min.xianchang
 */
public class TableOperatorFactory {
	private TableOperatorFactory() {

	}

	/**
	 * <pre>
	 * 构建一个TableOperator
	 * </pre>
	 *
	 * @param type
	 * @param table
	 * @param jdbcTemplate
	 * @return
	 */
	public static TableOperator newOperator(String type, Table<?> table, JdbcTemplate jdbcTemplate) {
		if (DbType.MYSQL.equalsWithKey(type)) {
			return new MysqlTableOperator(table, jdbcTemplate);
		}
		throw new RuntimeException("找不到类型[" + type + "]的数据库处理者(TableOperator)");
	}
	
}
