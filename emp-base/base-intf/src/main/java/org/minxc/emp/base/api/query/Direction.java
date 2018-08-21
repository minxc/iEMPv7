package org.minxc.emp.base.api.query;

/*
 * 
* 项目名称：base-intf   
* 类名称：Direction   
* 类描述：字段排序方向。   
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:57:48   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:57:48   
* 修改备注：   
* @version  1.0  
*
 */
public enum Direction {
	ASC, DESC;

	public static Direction fromString(String value) {
		try {
			return Direction.valueOf(value.toUpperCase());
		} catch (Exception e) {
			return ASC;
		}
	}
}
