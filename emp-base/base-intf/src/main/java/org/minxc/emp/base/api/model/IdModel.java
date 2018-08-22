package org.minxc.emp.base.api.model;

import java.io.Serializable;

/**
 * ID 接口 <br>
 * 若实现改接口、创建时、会自动赋值ID字段
 */
/**
 * 
* 项目名称：base-intf   
* 类名称：IDModel   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午11:40:34   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午11:40:34   
* 修改备注：   
* @version  1.0  
*
 */
public interface IdModel extends Serializable{
	 /**
	  * 
	 
	 * @Title: getId 
	 
	 * @Description: 主键 
	 
	 * @param @return    设定文件 
	 
	 * @return String    返回类型 
	 
	 * @throws
	  */
    public String getId();

    /**
     * 
    
    * @Title: setId 
    
    * @Description: 设置主键
    
    * @param @param id    设定文件 
    
    * @return void    返回类型 
    
    * @throws
     */
    public void setId(String id);
}
