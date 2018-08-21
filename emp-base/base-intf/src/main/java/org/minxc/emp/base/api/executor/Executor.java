package org.minxc.emp.base.api.executor;

/**
 * 
* 项目名称：base-intf   
* 类名称：Executor   
* 类描述： 执行器接口
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:49:47   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:49:47   
* 修改备注：   
* @version  1.0  
*
 */
public interface Executor<T> extends Comparable<Executor<T>> {

    /**
     * <pre>
     * 执行器的key
     * </pre>
     *
     * @return
     */
    String getKey();

    /**
     * <pre>
     * 执行器的名称
     * </pre>
     *
     * @return
     */
    String getName();

    /**
     * <pre>
     * 返回这个执行器的类型key
     * </pre>
     *
     * @return
     */
    String type();

    /**
     * <pre>
     * 返回校验器的别名
     * 多个以,分隔，eg：a,b,c,...
     * </pre>
     *
     * @return
     */
    String getCheckerKey();

    /**
     * <pre>
     * 序号
     * 用于某些执行器有先后顺序的
     * </pre>
     *
     * @return
     */
    int getSn();

    /**
     * <pre>
     * 运行这个执行器
     * 运行前要通过校验
     * </pre>
     *
     * @param param 运行的参数
     */
    void execute(T param);

}
