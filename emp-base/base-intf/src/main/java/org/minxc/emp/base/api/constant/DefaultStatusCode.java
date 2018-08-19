package org.minxc.emp.base.api.constant;

public class DefaultStatusCode implements StatusCode {
    private String code;
    private String system;
    private String desc;


    public DefaultStatusCode(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.system = statusCode.getSystem();
        this.desc = statusCode.getDesc();
    }

    public DefaultStatusCode() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
