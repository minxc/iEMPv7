package org.minxc.emp.base.api.constant;

/**
 * 
* 项目名称：base-intf   
* 类名称：Direction   
* 类描述：字段排序方式   
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:45:46   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:45:46   
* 修改备注：   
* @version  1.0  
*
 */
public enum Direction {
	
    ASC("asc", "升序"), DESC("desc", "降序");
    /**
     * key
     */
    private String key;
    /**
     * 描述
     */
    private String desc;

    private Direction(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
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

    public static Direction fromString(String value) {
        try {
            return Direction.valueOf(value.toUpperCase());
        } catch (Exception e) {
            return ASC;
        }
    }
}