package org.minxc.emp.base.manager.impl;

import org.minxc.emp.base.api.Page;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.base.manager.Manager;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 描述：抽象实体业务管理类实现
 * </pre>
 */
public abstract class BaseManager<PK extends Serializable, T extends Serializable> implements Manager<PK, T> {
    //获取基础类

    @Autowired
    protected BaseDao<PK, T> dao;
    
    public void create(T entity) {
        dao.create(entity);
    }

   
    public void update(T entity) {
        dao.update(entity);
    }

    public void remove(PK entityId) {
        dao.remove(entityId);
    }


    public T get(PK entityId) {
        return dao.get(entityId);
    }

   
    public void removeByIds(PK... ids) {
        if (ids != null) {
            for (PK pk : ids) {
                remove(pk);
            }
        }
    }

  
    public List<T> query(QueryFilter queryFilter) {
        return dao.query(queryFilter);
    }

    /**
     * <pre>
     * 根据QueryFilter获取唯一值
     * </pre>
     * @deprecated 请尽量写SQL。列表查询不会包含大数据文本 比如 form.html
     * @param queryFilter
     * @return
     */
    public T queryOne(QueryFilter queryFilter) {
        List<T> list = query(queryFilter);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

  
    public List<T> getAll() {
        return dao.query();
    }
    
	@Override
	public List<T> getAllByPage(Page page) {
		return null;
	}

}
