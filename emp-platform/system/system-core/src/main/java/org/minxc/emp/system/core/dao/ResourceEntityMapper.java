package org.minxc.emp.system.core.dao;

import org.minxc.emp.system.core.model.ResourceEntity;

/**
 * @version V1.0
 * @Title: ResourceEntityMapper
 * @Package: org.minxc.emp.system.core.dao
 * @Description: TODO:(用一句话描述该文件做什么)
 * @author: Xianchang.min
 * @date 2018/8/22 20:54
 */

public interface ResourceEntityMapper {

    int deleteByPrimaryKey(String id);

    int insert(ResourceEntity record);

    int insertSelective(ResourceEntity record);

    ResourceEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResourceEntity record);

    int updateByPrimaryKey(ResourceEntity record);
}