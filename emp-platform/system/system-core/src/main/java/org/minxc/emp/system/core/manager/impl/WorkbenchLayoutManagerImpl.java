package org.minxc.emp.system.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.system.core.dao.WorkbenchLayoutDao;
import org.springframework.stereotype.Service;

import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.system.core.manager.WorkbenchLayoutManager;
import org.minxc.emp.system.core.model.WorkbenchLayout;


@Service("workbenchLayoutManager")
public class WorkbenchLayoutManagerImpl extends BaseManager<String, WorkbenchLayout> implements WorkbenchLayoutManager {
    @Resource
    WorkbenchLayoutDao workbenchLayoutDao;

    @Override
    public void savePanelLayout(List<WorkbenchLayout> layOutList, String userId) {
        workbenchLayoutDao.removeByUserId(userId);

        for (WorkbenchLayout layOut : layOutList) {
            layOut.setUserId(userId);
            workbenchLayoutDao.create(layOut);
        }
    }


    @Override
    public List<WorkbenchLayout> getByUserId(String userId) {
        List<WorkbenchLayout> list = workbenchLayoutDao.getByUserId(userId);

        if (BeanUtils.isEmpty(list)) {
            list = workbenchLayoutDao.getByUserId(WorkbenchLayout.DEFAULT_LAYOUT);
        }
        return list;
    }


}
