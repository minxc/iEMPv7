package org.minxc.emp.base.api.constant;

import org.minxc.emp.base.api.exception.BusinessException;

import java.util.Arrays;

/**
* 项目名称：base-intf   
* 类名称：ColumnType   
* 类描述：Column中的type枚举   
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:44:18   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:44:18   
* 修改备注：   
* @version  1.0  
*
 */
public enum ColumnType {
    /**
     * 字符串
     */
    VARCHAR("varchar", "字符串", new String[]{"varchar", "char", "tinyblob", "tinytext"}),
    /**
     * 大文本
     */
    CLOB("clob", "大文本", new String[]{"text", "blob", "mediumblob", "mediumtext", "longblob", "longtext"}),
    /**
     * 数字型
     */
    NUMBER("number", "数字型", new String[]{"tinyint", "smallint", "mediumint", "int", "integer", "bigint", "float", "double", "decimal", "numeric"}),
    /**
     * 日期型
     */
    DATE("date", "日期型", new String[]{"date", "time", "year", "datetime", "timestamp"});
    /**
     * key
     */
    private String key;
    /**
     * 描述
     */
    private String desc;
    
    /**
     * 支持的数据库类型
     */
    private String[] supports;

    private ColumnType(String key, String desc, String[] supports) {
        this.key = key;
        this.desc = desc;
        this.supports = supports;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public String[] getSupports() {
        return supports;
    }

    /**
     * <pre>
     * 根据key来判断是否跟当前一致
     * </pre>
     *
     * @param key
     * @return
     */
    public boolean equalsWithKey(String key) {
        return this.key.equals(key);
    }

    /**
     * <pre>
     * 根据数据库的字段类型获取type
     * </pre>
     *
     * @param dbDataType 数据库的字段类型
     * @return
     */
    public static ColumnType getByDbDataType(String dbDataType) {
        for (ColumnType type : ColumnType.values()) {
            if (Arrays.asList(type.supports).contains(dbDataType)) {
                return type;
            }
        }
        throw new BusinessException(String.format("数据库类型[%s]转换不了系统支持的类型", dbDataType));
    }
}
