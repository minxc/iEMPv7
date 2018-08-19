package org.minxc.emp.syetem2.manager;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.syetem2.model.SystemTree;

/**
 * 系统树 Manager处理接口
 *
 * @author aschs
 * @email aschs@qq.com
 * @time 2018-03-13 19:58:28
 */
public interface SystemTreeManager extends Manager<String, SystemTree> {
    /**
     * <pre>
     * 根据别名获取对象
     * </pre>
     *
     * @param key
     * @return
     */
    SystemTree getByKey(String key);

    void removeContainNode(String id);
}
