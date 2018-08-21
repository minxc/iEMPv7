package org.minxc.emp.base.api.constant;

/**
 * 
* 项目名称：base-intf   
* 类名称：DefaultStatusCode   
* 类描述：状态码基本定义
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:45:21   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:45:21   
* 修改备注：   
* @version  1.0  
*
 */
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
