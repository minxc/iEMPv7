package org.minxc.emp.system.core.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.system.core.dao.LogErrDao;
import org.minxc.emp.system.core.manager.LogErrManager;
import org.minxc.emp.system.core.model.LogErr;

/**
 * <pre>
 * 描述：错误日志 处理实现类
 * </pre>
 */
@Service("sysLogErrManager")
public class LogErrorManagerImpl extends BaseManager<String, LogErr> implements LogErrManager {
    @Resource
    LogErrDao sysLogErrDao;

    @CatchError
    @Override
    public void getSub() {
        System.out.println("11111111");
    }
}
