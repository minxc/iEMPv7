package org.minxc.emp.base.manager;

import org.minxc.emp.base.api.Page;
import org.minxc.emp.base.api.query.QueryFilter;

import java.io.Serializable;
import java.util.List;

/**
 *业务类管理实体类接口，参数对象为主键和业务实体类（对应数据库访问映射实体）
 */
public interface Manager<PK extends Serializable, T> {
    /**
     * 创建实体对象
     *
     * @param entity
     * @return
     */
    public void create(T entity);

    /**
     * 更新实体对象
     *
     * @param entity
     * @return
     */
    public void update(T entity);

    /**
     * 按实体ID删除对象
     *
     * @param entityId
     */
    public void remove(PK entityId);

    /**
     * 按实体ID获取实体
     *
     * @param entityId
     */
    public T get(PK entityId);

    /**
     * 按实体IDs删除记录
     *
     * @param ids
     */
    public void removeByIds(PK... ids);

    /**
     * 查询实体对象
     *
     * @param queryFilter
     * @return
     */
    public List<T> query(QueryFilter queryFilter);

    /**
     * 取得所有查询对象
     *
     * @return
     */
    public List<T> getAll();

    /**
     * 取得所有查询对象并分页查询
     *
     * @param page
     * @return
     */
    public List<T> getAllByPage(Page page);
}