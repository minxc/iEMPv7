package org.minxc.emp.base.db.table.impl.sqlserver;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.base.core.util.AppUtil;
import org.minxc.emp.base.db.api.table.ITableOperator;
import org.minxc.emp.base.db.api.table.model.Index;
import org.minxc.emp.base.db.table.BaseIndexOperator;
import org.minxc.emp.base.db.table.model.DefaultIndex;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQLServer 索引操作的实现
 *
 * <pre>
 * </pre>
 */
public class SQLServerIndexOperator extends BaseIndexOperator {

    // 批量操作的
    protected int BATCH_SIZE = 100;


    /*
     * (non-Javadoc)
     *
     * @see
     * BaseIndexOperator#createIndex(java.lang.String,
     * java.lang.String, java.lang.String, java.util.List)
     */
    @Override
    public void createIndex(Index index) throws SQLException {
        String sql = genIndexDDL(index);
        jdbcTemplate.execute(sql);
    }


    /**
     * 生成Index对象对应的DDL语句
     *
     * @param index
     * @return
     */
    private String genIndexDDL(Index index) {
        StringBuffer sql = new StringBuffer();
        sql.append("CREATE ");
        // 是否唯一
        if (index.isUnique()) {
            sql.append(" UNIQUE ");
        }
        // 索引类型
        if (!StringUtils.isEmpty(index.getIndexType())) {
            if (index.getIndexType().equalsIgnoreCase("CLUSTERED")) {
                sql.append(" CLUSTERED ");
            }
        }
        sql.append(" INDEX ");
        // 索引名
        sql.append(index.getIndexName());
        // 表名
        sql.append(" ON ");
        sql.append(index.getTableName());
        // 列
        sql.append(" (");
        for (String field : index.getColumnList()) {
            sql.append(field);
            sql.append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");

        return sql.toString();
    }

    /*
     * (non-Javadoc) 刪除索引
     *
     * @see
     * BaseIndexOperator#dropIndex(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void dropIndex(String tableName, String indexName) {
        String sql = "DROP INDEX " + indexName + " ON " + tableName;
        jdbcTemplate.execute(sql);
    }


    /**
     * 通过SQL获得索引
     *
     * @param sql
     * @return
     */
    private List<Index> getIndexesBySql(String sql) {
        List<Index> indexes = jdbcTemplate.query(sql, new RowMapper<Index>() {
            @Override
            public Index mapRow(ResultSet rs, int rowNum) throws SQLException {
                Index index = new DefaultIndex();
                List<String> columns = new ArrayList<String>();
                index.setIndexName(rs.getString("INDEX_NAME"));
                index.setIndexType(mapIndexType(rs.getInt("INDEX_TYPE")));
                index.setTableName(rs.getString("TABLE_NAME"));
                index.setTableType(mapTableType(rs.getString("TABLE_TYPE")));
                index.setUnique(mapIndexUnique(rs.getInt("IS_UNIQUE")));
                index.setPkIndex(mapPKIndex(rs.getInt("IS_PK_INDEX")));
                columns.add(rs.getString("COLUMN_NAME"));
                index.setIndexStatus(mapIndexStatus(rs.getInt("IS_DISABLED")));
                index.setColumnList(columns);
                return index;
            }

        });
        return indexes;

    }

    private String mapTableType(String type) {
        type = type.trim();
        String tableType = null;
        if (type.equalsIgnoreCase("U")) {
            tableType = Index.TABLE_TYPE_TABLE;
        } else if (type.equalsIgnoreCase("V")) {
            tableType = Index.TABLE_TYPE_VIEW;
        }
        return tableType;

    }

    /**
     * 0 = Heap 1 = Clustered 2 = Nonclustered 3 = XML 4 = Spatial
     *
     * @param type
     * @return
     */
    private String mapIndexType(int type) {
        String indexType = null;
        switch (type) {
            case 0:
                indexType = Index.INDEX_TYPE_HEAP;
                break;
            case 1:
                indexType = Index.INDEX_TYPE_CLUSTERED;
                break;
            case 2:
                indexType = Index.INDEX_TYPE_NONCLUSTERED;
                break;
            case 3:
                indexType = Index.INDEX_TYPE_XML;
                break;
            case 4:
                indexType = Index.INDEX_TYPE_SPATIAL;
                break;
            default:
                break;
        }
        return indexType;
    }

    private boolean mapIndexUnique(int type) {
        boolean indexUnique = false;
        switch (type) {
            case 0:
                indexUnique = false;
                break;
            case 1:
                indexUnique = true;
                break;
            default:
                break;
        }
        return indexUnique;
    }

    private boolean mapPKIndex(int type) {
        boolean pkIndex = false;
        switch (type) {
            case 0:
                pkIndex = false;
                break;
            case 1:
                pkIndex = true;
                break;
            default:
                break;
        }
        return pkIndex;
    }

    private String mapIndexStatus(int type) {
        String tableType = null;
        switch (type) {
            case 0:
                tableType = Index.INDEX_STATUS_VALIDATE;
                break;
            case 1:
                tableType = Index.INDEX_STATUS_INVALIDATE;
                break;
        }
        return tableType;
    }

    /**
     * indexes中，每一索引列，对就一元素。需要进行合并。
     *
     * @param indexes
     * @return
     */
    private List<Index> mergeIndex(List<Index> indexes) {
        List<Index> indexList = new ArrayList<Index>();
        for (Index index : indexes) {
            boolean found = false;
            for (Index index1 : indexList) {
                if (index.getIndexName().equals(index1.getIndexName())
                        && index.getTableName().equals(index1.getTableName())) {
                    index1.getColumnList().add(index.getColumnList().get(0));
                    found = true;
                    break;
                }
            }
            if (!found)
                indexList.add(index);
        }
        return indexList;
    }

    /**
     * 判断索引是否是主键索引。如果是，则将索引index的pkIndex属性设置为true。此方法是批量操作，主要是减少对数据库访问次数。
     *
     * @param indexList
     * @return
     * @throws SQLException
     */
    private List<Index> dedicatePKIndex(List<Index> indexList)
            throws SQLException {
        // 用于存放所有索引所包含的所有数据表名(去掉重复)
        List<String> tableNames = new ArrayList<String>();
        for (Index index : indexList) {
            // 将索引对应的表名放到tableNames中。
            if (!tableNames.contains(index.getTableName())) {
                tableNames.add(index.getTableName());
            }
        }
        Map<String, List<String>> tablePKColsMaps = getTablesPKColsByNames(tableNames);
        for (Index index : indexList) {
            if (isListEqual(index.getColumnList(),
                    tablePKColsMaps.get(index.getTableName()))) {
                index.setPkIndex(true);
            } else {
                index.setPkIndex(false);
            }
        }

        return indexList;
    }

    /**
     * 根据表名，取得对应表的主键列。此方法是指操作，主要是减少对数据库的访问次数。返回的Map中：key=表名；value=表名对应的主键列列表。
     *
     * @param tableNames
     * @return
     * @throws SQLException
     */
    private Map<String, List<String>> getTablesPKColsByNames(
            List<String> tableNames) throws SQLException {
        Map<String, List<String>> tableMaps = new HashMap<String, List<String>>();
        List<String> names = new ArrayList<String>();
        for (int i = 1; i <= tableNames.size(); i++) {
            names.add(tableNames.get(i - 1));
            if (i % BATCH_SIZE == 0 || i == tableNames.size()) {
                Map<String, List<String>> map;
                map = getPKColumns(names);
                tableMaps.putAll(map);
                names.clear();
            }
        }
        return tableMaps;
    }

    /**
     * 获取主键字段
     *
     * @param tableNames
     * @return
     * @throws SQLException
     */
    private Map<String, List<String>> getPKColumns(List<String> tableNames)
            throws SQLException {
        ITableOperator tableOperator = AppUtil.getBean(ITableOperator.class);
        return tableOperator.getPKColumns(tableNames);
    }

    /**
     * 获取主键字段
     *
     * @param tableName
     * @return
     * @throws SQLException
     */
    private List<String> getPKColumns(String tableName) throws SQLException {
        ITableOperator tableOperator = AppUtil.getBean(ITableOperator.class);
        return tableOperator.getPKColumns(tableName);
    }


    /**
     * 判断索引是否是主键索引。如果是，则将索引index的pkIndex属性设置为true。
     *
     * @param index
     * @return
     */
    private Index dedicatePKIndex(Index index) {
        try {
            List<String> pkCols = getPKColumns(index.getIndexName());
            if (isListEqual(index.getColumnList(), pkCols))
                index.setPkIndex(true);
            else
                index.setPkIndex(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return index;
    }

    /**
     * 比较两个列表是否相等。在比较两个列表的元素时，比较的方式为(o==null ? e==null : o.equals(e)).
     *
     * @param list1
     * @param list2
     * @return
     */
    private boolean isListEqual(List<String> list1, List<String> list2) {
        if (list1 == null && list2 == null)// 2个都为null
            return true;
        if (list1 == null || list2 == null)// 2个有一个为null
            return false;
        if (list1.size() != list2.size())// 2个长度不相等
            return false;
        if (list1.containsAll(list2))
            return true;

        return false;
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * BaseIndexOperator#rebuildIndex(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void rebuildIndex(String tableName, String indexName) {
        String sql = "DBCC DBREINDEX ('" + tableName + "','" + indexName + "',80)";
        jdbcTemplate.execute(sql);
    }


    @Override
    public Index getIndex(String tableName, String indexName) throws SQLException {
        String sql = "SELECT IDX.NAME AS INDEX_NAME,IDX.TYPE AS INDEX_TYPE,"
                + "OBJ.NAME AS TABLE_NAME,OBJ.TYPE AS TABLE_TYPE, "
                + "IDX.IS_DISABLED AS IS_DISABLED,IDX.IS_UNIQUE AS IS_UNIQUE, "
                + "IDX.IS_PRIMARY_KEY AS IS_PK_INDEX, "
                + "COL.NAME AS COLUMN_NAME "
                + "FROM  SYS.INDEXES  IDX  "
                + "JOIN SYS.OBJECTS OBJ ON IDX.OBJECT_ID=OBJ.OBJECT_ID  "
                + "JOIN SYS.INDEX_COLUMNS IDC ON OBJ.OBJECT_ID=IDC.OBJECT_ID AND IDX.INDEX_ID=IDC.INDEX_ID "
                + "JOIN SYS.COLUMNS COL ON COL.OBJECT_ID=IDC.OBJECT_ID AND COL.COLUMN_ID = IDC.COLUMN_ID "
                + "WHERE  IDX.NAME ='" + indexName + "'";

        List<Index> indexList = getIndexesBySql(sql);

        indexList = mergeIndex(indexList);
        for (Index index : indexList) {
            index.setIndexDdl(genIndexDDL(index));
        }

        if (indexList.size() > 0) {
            Index index = indexList.get(0);
            return dedicatePKIndex(index);
        }
        return null;
    }


    @Override
    public List<Index> getIndexByFuzzyMatch(String indexName)
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<Index> getIndexsByTable(String tableName) throws SQLException {
        String sql = "SELECT IDX.NAME AS INDEX_NAME,IDX.TYPE AS INDEX_TYPE,"
                + "OBJ.NAME AS TABLE_NAME,OBJ.TYPE AS TABLE_TYPE, "
                + "IDX.IS_DISABLED AS IS_DISABLED,IDX.IS_UNIQUE AS IS_UNIQUE, "
                + "IDX.IS_PRIMARY_KEY AS IS_PK_INDEX, "
                + "COL.NAME AS COLUMN_NAME "
                + "FROM  SYS.INDEXES  IDX  "
                + "JOIN SYS.OBJECTS OBJ ON IDX.OBJECT_ID=OBJ.OBJECT_ID  "
                + "JOIN SYS.INDEX_COLUMNS IDC ON OBJ.OBJECT_ID=IDC.OBJECT_ID AND IDX.INDEX_ID=IDC.INDEX_ID "
                + "JOIN SYS.COLUMNS COL ON COL.OBJECT_ID=IDC.OBJECT_ID AND COL.COLUMN_ID = IDC.COLUMN_ID "
                + "WHERE OBJ.NAME ='" + tableName + "' ";

        List<Index> indexList = getIndexesBySql(sql);

        indexList = mergeIndex(indexList);
        for (Index index : indexList) {
            index.setIndexDdl(genIndexDDL(index));
        }

        return indexList;
    }
}
