package org.minxc.emp.base.api.executor.checker;

/*
 * 
* 项目名称：base-intf   
* 类名称：ExecutorChecker   
* 类描述：  执行器的校验者 
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:50:47   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:50:47   
* 修改备注：   
* @version  1.0  
*
 */
public interface ExecutorChecker {
    /**
     * <pre>
     * 校验器的key
     * 默认是类名
     * </pre>
     *
     * @return
     */
    String getKey();

    /**
     * <pre>
     * 校验器的名字
     * </pre>
     *
     * @return
     */
    String getName();

    /**
     * <pre>
     * 校验执行器
     * </pre>
     *
     * @param exectorKey 执行器key
     * @return
     */
    boolean check(String exectorKey);
}
