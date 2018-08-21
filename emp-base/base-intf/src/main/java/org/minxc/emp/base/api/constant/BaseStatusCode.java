package org.minxc.emp.base.api.constant;

/*
 * 
* 项目名称：base-intf   
* 类名称：BaseStatusCode   
* 类描述：   系统基础状态码定义
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:43:48   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:43:48   
* 修改备注：   
* @version  1.0  
*
 */
public enum BaseStatusCode implements StatusCode {

    /**
     * 成功
     */
    SUCCESS("200", "成功"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR("500", "系统异常"),

    /**
     * 访问超时
     */
    TIMEOUT("401", "访问超时"),

    /**
     * 访问受限
     */
    NO_ACCESS("403", "访问受限"),

    /**
     * 参数校验不通过
     */
    PARAM_ILLEGAL("100", "参数校验不通过"),

    /**
     * 数据已存在
     */
    DATA_EXISTS("101", "数据已存在");

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    /**
     * 系统
     */
    private String system;

    BaseStatusCode(String code, String description) {
        this.code = code;
        this.desc = description;
        this.system = "BASE";
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String getSystem() {
        return system;
    }
}
	 
