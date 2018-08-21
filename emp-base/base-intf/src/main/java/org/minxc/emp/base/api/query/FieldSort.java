package org.minxc.emp.base.api.query;


/**
 * 
* 项目名称：base-intf   
* 类名称：FieldSort   
* 类描述：描述字段排序信息   --按照哪个字段进行何种方式的排序   
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:58:02   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:58:02   
* 修改备注：   
* @version  1.0  
*
 */
public interface FieldSort {


    /**
     * 返回排序的字段名
     *
     * @return
     */
    public String getField();

    /**
     * 返回排序的类型 FieldSort.SORT_ASC 或	 FieldSort.SORT_DESC
     *
     * @return
     */
    public Direction getDirection();
}
