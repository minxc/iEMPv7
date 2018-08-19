package org.minxc.emp.organization.core.dao;

import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.organization.core.model.GroupModelImpl;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;


/**
 * <pre>
 * 描述：组织架构 DAO接口
 * </pre>
 */
@MapperScan 
public interface GroupDao extends BaseDao<String, GroupModelImpl> {
    /**
     * 根据Code取定义对象。
     *
     * @param code
     * @return
     */
    GroupModelImpl getByCode(String code);

    /**
     * 根据用户ID获取组织列表
     *
     * @param userId
     * @return
     */
    List<GroupModelImpl> getByUserId(String userId);


}
