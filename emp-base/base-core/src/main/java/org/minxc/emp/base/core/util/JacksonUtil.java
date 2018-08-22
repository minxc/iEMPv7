
/**  

* @Title: JacksonUtil.java 

* @Package org.minxc.emp.base.core.util 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月23日 上午12:07:40 

* @version V1.0  

*/ 

package org.minxc.emp.base.core.util;

import com.fasterxml.jackson.databind.JsonNode;

/**      
* 项目名称：base-core   
* 类名称：JacksonUtil   
* 类描述：   Json工具类，使用Jackson处理JSON数据
* 创建人：Xianchang.min   
* 创建时间：2018年8月23日 上午12:07:40   
* 修改人：Xianchang.min   
* 修改时间：2018年8月23日 上午12:07:40   
* 修改备注：   
* @version  1.0  
**/

public class JacksonUtil {
	
	
	 /**
     * 根据键获取值。
     *
     * @param obj
     * @param key
     * @param defaultValue
     * @return String
     */
    public static String getString(JsonNode jsonNode, String key, String defaultValue) {
    	if(jsonNode.has(key)) {
    		return jsonNode.get(key).textValue();
    	}
    	return defaultValue;
    }
}
