package org.minxc.emp.syetem2.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.syetem2.manager.SystemTreeNodeManager;
import org.springframework.stereotype.Service;

import org.minxc.emp.base.api.constant.Direction;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.query.QueryOperation;
import org.minxc.emp.base.db.model.query.DefaultQueryFilter;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.syetem2.dao.SystemTreeNodeDao;
import org.minxc.emp.syetem2.model.SystemTreeNodeImpl;

/**
 * 系统树节点 Manager处理实现类
 *
 * @author min.xianchang
 * @email xianchangmin@126.com
 * @time 2018-03-13 20:02:33
 */
@Service("sysTreeNodeManager")
public class SystemTreeNodeManagerImpl extends BaseManager<String, SystemTreeNodeImpl> implements SystemTreeNodeManager {
    @Resource
    SystemTreeNodeDao sysTreeNodeDao;

    @Override
    public List<SystemTreeNodeImpl> getByTreeId(String treeId) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("tree_id_", treeId, QueryOperation.EQUAL);
        filter.addFieldSort("sn_", Direction.ASC.getKey());
        return this.query(filter);
    }

    @Override
    public SystemTreeNodeImpl getByTreeIdAndKey(String treeId, String key) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("tree_id_", treeId, QueryOperation.EQUAL);
        filter.addFilter("key_", key, QueryOperation.EQUAL);
        return this.queryOne(filter);
    }

    @Override
    public List<SystemTreeNodeImpl> getByParentId(String parentId) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("parent_id_", parentId, QueryOperation.EQUAL);
        return this.query(filter);
    }

    @Override
    public List<SystemTreeNodeImpl> getStartWithPath(String path) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("path_", path , QueryOperation.RIGHT_LIKE);
        return this.query(filter);
    }

    @Override
    public void removeByTreeId(String treeId) {
        sysTreeNodeDao.removeByTreeId(treeId);
    }
    
    @Override
    public void removeByPath(String path) {
    	sysTreeNodeDao.removeByPath(path);
    }
}
