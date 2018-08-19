package org.minxc.emp.system.api2.service;

import org.minxc.emp.system.api2.model.SystemTreeNode;

/**
 * <pre>
 * 描述：SysTreeNodeService接口
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:2018年3月28日 下午3:31:25
 * 版权:summer
 * </pre>
 */
public interface SystemTreeNodeService {
    /**
     * <pre>
     * 根据id获取对象
     * </pre>
     *
     * @param id
     * @return
     */
    SystemTreeNode getById(String id);

}
