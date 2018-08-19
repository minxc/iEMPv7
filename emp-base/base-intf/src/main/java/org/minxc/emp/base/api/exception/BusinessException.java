package org.minxc.emp.base.api.exception;

import org.minxc.emp.base.api.constant.BaseStatusCode;
import org.minxc.emp.base.api.constant.StatusCode;

/**
 * @author jeff
 * 2017-11-19 20:38:08
 * @说明 业务异常，通常用于业务代码反馈异常信息状态码
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
        if (statusCode == null) return "";
        return statusCode.getCode();
    }


    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
