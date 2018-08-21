package org.minxc.emp.base.api.aop.annotion;

import java.lang.annotation.*;


/**  
* 项目名称：base-intf   
* 类名称：CatchError   
* 类描述： 
* 如果该注解的方法出现异常，则会反馈标准的异常结果【ResultMsg.java】给前端或者服务调用方
* 若异常为BusException则不会记录日志<br>
* 更多信息请查看ErrAspect.java
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:40:35   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:40:35   
* 修改备注：   
* @version  1.0  
*
*/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CatchError {
	
	
    String value() default "";

    boolean write2response() default false;
    
}