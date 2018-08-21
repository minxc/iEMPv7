package org.minxc.emp.base.api.response;

import org.minxc.emp.base.api.constant.StatusCode;

import java.io.Serializable;


/**
 * 
* 项目名称：base-intf   
* 类名称：RequestResult   
* 类描述：  请求结果描述--
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午11:00:46   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午11:00:46   
* 修改备注：   
* @version  1.0  
*
 */
public interface RequestResult extends Serializable {
	
    public abstract Boolean getIsOk();

    public abstract StatusCode getStatusCode();

    public abstract String getCode();

    public abstract String getMsg();

    public abstract String getCause();
}
