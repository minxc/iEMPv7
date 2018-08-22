package org.minxc.emp.system.core.dao;

import org.minxc.emp.system.core.model.ResourceLinkEntity;

/**
 * @version V1.0
 * @Title: ResourceLinkEntityMapper
 * @Package: org.minxc.emp.system.core.dao
 * @Description: 关联资源Dao访问接口
 * @author: Xianchang.min
 * @date 2018/8/22 21:11
 */

public interface ResourceLinkEntityMapper {

    int deleteByPrimaryKey(String id);

    int insert(ResourceLinkEntity record);

    int insertSelective(ResourceLinkEntity record);

    ResourceLinkEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResourceLinkEntity record);

    int updateByPrimaryKey(ResourceLinkEntity record);
}
