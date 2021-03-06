package org.minxc.emp.business.api.constant;

/**
 * <pre>
 * 描述：BusTableRel中type属性的枚举
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年2月26日 下午3:19:05
 * 版权:summer
 * </pre>
 */
public enum BusinessTableRelType {
	/**
	 * 主表
	 */
	MAIN("main", "主表"),
	/**
	 * 一对一
	 */
	ONE_TO_ONE("oneToOne", "一对一"),
	/**
	 * 一对多
	 */
	ONE_TO_MANY("oneToMany", "一对多");
	/**
	 * key
	 */
	private String key;
	/**
	 * 描述
	 */
	private String desc;

	private BusinessTableRelType(String key, String desc) {
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

	/**
	 * <pre>
	 * 根据key获取
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	public static BusinessTableRelType getByKey(String key) {
		for (BusinessTableRelType type : BusinessTableRelType.values()) {
			if (key.equals(type.getKey())) {
				return type;
			}
		}
		return null;
	}
}
