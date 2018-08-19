package org.minxc.emp.syetem2.manager.impl;

import javax.annotation.Resource;

import org.minxc.emp.syetem2.manager.SystemTreeNodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.query.QueryOperation;
import org.minxc.emp.base.db.model.query.DefaultQueryFilter;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.syetem2.dao.SystemTreeDao;
import org.minxc.emp.syetem2.manager.SystemTreeManager;
import org.minxc.emp.syetem2.model.SystemTree;

/**
 * 系统树 Manager处理实现类
 *
 * @author aschs
 * @email aschs@qq.com
 * @time 2018-03-13 19:58:28
 */
@Service("sysTreeManager")
public class SystemTreeManagerImpl extends BaseManager<String, SystemTree> implements SystemTreeManager {
    @Resource
    SystemTreeDao sysTreeDao;
    @Autowired
    SystemTreeNodeManager sysTreeNodeManager;

    @Override
    public SystemTree getByKey(String key) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("key_", key, QueryOperation.EQUAL);
        return this.queryOne(filter);
    }

    @Override
    public void removeContainNode(String id) {
        this.remove(id);
        sysTreeNodeManager.removeByTreeId(id);
    }
}
