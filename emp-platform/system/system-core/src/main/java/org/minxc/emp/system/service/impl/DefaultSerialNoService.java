package org.minxc.emp.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.system.api.service.SerialNoService;
import org.minxc.emp.system.core.manager.SerialNoManager;

/**
 *  流水号 接口实现
 */
@Service("serialNoService")
public class DefaultSerialNoService implements SerialNoService {

    @Resource
    SerialNoManager serialNoManager;

    @Override
    public String genNextNo(String alias) {
        return serialNoManager.nextId(alias);
    }

    @Override
    public String getPreviewNo(String alias) {
        return serialNoManager.getCurIdByAlias(alias);
    }
}
