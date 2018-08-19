package org.minxc.emp.syetem2.service;

import org.minxc.emp.system.api2.model.SystemTreeNode;
import org.minxc.emp.system.api2.service.SystemTreeNodeService;
import org.minxc.emp.syetem2.manager.SystemTreeNodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 描述：SysTreeNodeService接口
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:2018年3月28日 下午3:31:25
 * 版权:summer
 * </pre>
 */
@Service
public class SystemTreeNodeServiceImpl implements SystemTreeNodeService {
    @Autowired
    SystemTreeNodeManager sysTreeNodeManager;

    @Override
    public SystemTreeNode getById(String id) {
        return sysTreeNodeManager.get(id);
    }
}
