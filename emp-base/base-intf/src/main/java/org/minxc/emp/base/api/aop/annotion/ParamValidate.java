package org.minxc.emp.base.api.aop.annotion;

import java.lang.annotation.*;


/**
* 项目名称：base-intf   
* 类名称：ParamValidate   
* 类描述：   校验入参中对象，对象需要对需要校验字段进行注解， 注解位于
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:41:26   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:41:26   
* 修改备注：   
* @version  1.0  
*
*/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamValidate {
    String value() default "";
}