package org.minxc.emp.business.api.model;


/**
 * <pre>
 * 描述：BusinessColumn对其他模块的支持
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年4月10日 下午6:10:28
 * 版权:summer
 * </pre>
 */
public interface BusinessColumn {
	String getKey();
	
	String getName();

	String getType();

	int getLength();

	int getDecimal();

	boolean isRequired();

	boolean isPrimary();

	String getDefaultValue();

	String getComment();

	BusinessColumnControl getCtrl();

	String getTableId();

	BusinessTable getTable();
	
	/**
	 * <pre>
	 * 获取字段的初始化值
	 * 根据defaultValue和type映射成对象
	 * </pre>
	 * 
	 * @return
	 */
	Object initValue();
	
	/**
	 * <pre>
	 * 把某个字符串按照字段类型转成真正的值
	 * </pre>	
	 * @param str
	 * @return
	 */
	Object value(String str);

}
