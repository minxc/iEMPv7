package org.minxc.emp.system.api2.model;

/**
 * <pre>
 * 描述：系统树节点SysTreeNode接口类
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年3月28日 下午3:27:50
 * 版权:summer
 * </pre>
 */
public interface SystemTreeNode {
    /**
     * <pre>
     * 主键
     * </pre>
     *
     * @return
     */
    String getId();

    /**
     * <pre>
     * 别名
     * </pre>
     *
     * @return
     */
    String getKey();

    /**
     * <pre>
     * 名字
     * </pre>
     *
     * @return
     */
    String getName();

    /**
     * <pre>
     * 描述
     * </pre>
     *
     * @return
     */
    String getDesc();

    /**
     * <pre>
     * 父ID
     * </pre>
     *
     * @return
     */
    String getParentId();

    /**
     * <pre>
     * 路径 eg:pppid.ppid.pid
     * </pre>
     *
     * @return
     */
    String getPath();

    /**
     * <pre>
     * 排序号
     * </pre>
     *
     * @return
     */
    int getSn();

    /**
     * <pre>
     * 所属树id
     * </pre>
     *
     * @return
     */
    String getTreeId();
}
