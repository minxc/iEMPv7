package org.minxc.emp.base.api.exception;

import org.minxc.emp.base.api.constant.BaseStatusCode;
import org.minxc.emp.base.api.constant.StatusCode;


/*
 * 
* 项目名称：base-intf   
* 类名称：BusinessException   
* 类描述：   业务异常，通常用于业务代码反馈异常信息状态码
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:47:43   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:47:43   
* 修改备注：   
* @version  1.0  
*
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -7289238698048230824L;
	public StatusCode statusCode = BaseStatusCode.SYSTEM_ERROR;

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public BusinessException(Throwable throwable) {
		super(throwable);
	}

	public BusinessException(String msg, StatusCode errorCode) {
		super(msg);
		this.statusCode = errorCode;
	}

	public BusinessException(StatusCode errorCode) {
		super(errorCode.getDesc());
		this.statusCode = errorCode;
	}

	public BusinessException(StatusCode errorCode, Throwable throwable) {
		super(errorCode.getDesc(), throwable);
		this.statusCode = errorCode;
	}

	public BusinessException(String msg, StatusCode errorCode, Throwable throwable) {
		super(errorCode.getDesc() + ":" + msg, throwable);
		this.statusCode = errorCode;
	}

	public String getStatuscode() {
		if (statusCode == null)
			return "";
		return statusCode.getCode();
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}
}
