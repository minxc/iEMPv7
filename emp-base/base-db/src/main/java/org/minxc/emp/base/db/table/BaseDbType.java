package org.minxc.emp.base.db.table;

import org.minxc.emp.base.db.api.table.IDbType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDbType implements IDbType {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    // JdbcTemplate
    protected JdbcTemplate jdbcTemplate;


    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
