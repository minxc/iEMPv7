package org.minxc.emp.system.core.manager;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.system.core.model.SystemProperties;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 描述：SYS_PROPERTIES 处理接口
 * </pre>
 */
public interface SystemPropertiesManager extends Manager<String, SystemProperties> {


    /**
     * 分组列表。
     *
     * @return
     */
    List<String> getGroups();

    /**
     * 判断别名是否存在。
     *
     * @param sysProperties
     * @return
     */
    boolean isExist(SystemProperties sysProperties);


    /**
     * 重新读取属性配置。
     *
     * @return
     */
    Map<String, Map<String, String>> reloadProperty();

}
