package org.minxc.emp.base.api.constant;


/**
 * 
* 项目名称：base-intf   
* 类名称：StatusCode   
* 类描述：   统状态码定义抽象接口    ---子模块或者系统需要定义自己的系统状态码
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:46:23   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:46:23   
* 修改备注：   
* @version  1.0  
*
 */
public interface StatusCode {
    /**
     * 状态码
     *
     * @return
     */
    public String getCode();

    /**
     * 异常信息
     *
     * @return
     */
    public String getDesc();

    /**
     * 系统编码
     *
     * @return
     */
    public String getSystem();

}
