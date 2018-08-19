package org.minxc.emp.system.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.system.core.dao.RelResourceDao;
import org.springframework.stereotype.Service;

import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.system.core.manager.RelResourceManager;
import org.minxc.emp.system.core.model.RelResource;

/**
 * <pre>
 * 描述：关联资源 处理实现类
 * </pre>
 */
@Service("relResourceManager")
public class RelResourceManagerImpl extends BaseManager<String, RelResource> implements RelResourceManager {
    @Resource
    RelResourceDao relResourceDao;

    @Override
    public List<RelResource> getByResourceId(String resId) {
        return relResourceDao.getByResourceId(resId);
    }

    @Override
    public void removeByResId(String resId) {
        relResourceDao.removeByResId(resId);
    }

}
