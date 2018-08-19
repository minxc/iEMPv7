package org.minxc.emp.system.core.manager;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.system.core.model.LogErr;

/**
 * 描述：错误日志 处理接口
 */
public interface LogErrManager extends Manager<String, LogErr> {

    void getSub();

}
