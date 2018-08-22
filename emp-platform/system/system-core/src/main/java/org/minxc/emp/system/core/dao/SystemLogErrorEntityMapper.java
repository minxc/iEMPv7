package org.minxc.emp.system.core.dao;

import org.minxc.emp.system.core.model.SystemLogErrorEntity;
import org.minxc.emp.system.core.model.SystemLogErrorEntityWithBLOBs;

/**
 * @version V1.0
 * @Title: SystemLogErrorEntityMapper
 * @Package: org.minxc.emp.system.core.dao
 * @Description: TODO:(用一句话描述该文件做什么)
 * @author: Xianchang.min
 * @date 2018/8/22 20:48
 */

public interface SystemLogErrorEntityMapper {

    int deleteByPrimaryKey(String id);

    int insert(SystemLogErrorEntityWithBLOBs record);

    int insertSelective(SystemLogErrorEntityWithBLOBs record);

    SystemLogErrorEntityWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemLogErrorEntityWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SystemLogErrorEntityWithBLOBs record);

    int updateByPrimaryKey(SystemLogErrorEntity record);
}
