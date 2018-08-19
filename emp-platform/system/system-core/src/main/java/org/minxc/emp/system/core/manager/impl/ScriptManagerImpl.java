package org.minxc.emp.system.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.system.core.dao.ScriptDao;
import org.minxc.emp.system.core.manager.ScriptManager;
import org.minxc.emp.system.core.model.Script;


@Service("scriptManager")
public class ScriptManagerImpl extends BaseManager<String, Script> implements ScriptManager {
    @Resource
    private ScriptDao scriptDao;

    @Override
    public List<String> getDistinctCategory() {
        return scriptDao.getDistinctCategory();
    }

    @Override
    public Integer isNameExists(String name) {
        return scriptDao.isNameExists(name);
    }

}
