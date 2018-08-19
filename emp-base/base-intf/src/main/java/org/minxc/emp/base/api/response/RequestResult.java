package org.minxc.emp.base.api.response;

import org.minxc.emp.base.api.constant.StatusCode;

import java.io.Serializable;

public interface RequestResult extends Serializable {
    public abstract Boolean getIsOk();

    public abstract StatusCode getStatusCode();

    public abstract String getCode();

    public abstract String getMsg();

    public abstract String getCause();
}
