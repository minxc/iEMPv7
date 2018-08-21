package org.minxc.emp.base.api.exception;

import org.minxc.emp.base.api.constant.BaseStatusCode;
import org.minxc.emp.base.api.constant.StatusCode;

/*
 * 
* 项目名称：base-intf   
* 类名称：SystemException   
* 类描述：   系统异常，相比businessExecption 表示更严重的异常
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:48:56   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:48:56   
* 修改备注：   
* @version  1.0  
*
 */
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = -7289238698048230824L;
	public StatusCode statusCode = BaseStatusCode.SYSTEM_ERROR;

	public SystemException(String msg) {
		super(msg);
	}

	public SystemException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public SystemException(Throwable throwable) {
		super(throwable);
	}

	public SystemException(String msg, StatusCode errorCode) {
		super(msg);
		this.statusCode = errorCode;
	}

	public SystemException(StatusCode errorCode) {
		super(errorCode.getDesc());
		this.statusCode = errorCode;
	}

	public SystemException(StatusCode errorCode, Throwable throwable) {
		super(errorCode.getDesc(), throwable);
		this.statusCode = errorCode;
	}

	public SystemException(String msg, StatusCode errorCode, Throwable throwable) {
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
