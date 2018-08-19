package org.minxc.emp.system.core.dao;

import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.system.core.model.RelResource;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

/**
 * <pre>
 * 描述：关联资源 DAO接口
 * </pre>
 */
@MapperScan
public interface RelResourceDao extends BaseDao<String, RelResource> {

    List<RelResource> getByResourceId(String resId);

    /**
     * 根据资源id删除关联资源。
     *
     * @param resId
     */
    void removeByResId(String resId);
}
