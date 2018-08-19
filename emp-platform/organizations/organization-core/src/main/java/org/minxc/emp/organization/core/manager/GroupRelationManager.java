package org.minxc.emp.organization.core.manager;

import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.organization.core.model.GroupRelation;

import java.util.List;

/**
 * <pre>
 * 描述：组织关联关系 处理接口
 * </pre>
 */
public interface GroupRelationManager extends Manager<String, GroupRelation> {


    GroupRelation getByCode(String code);

    /**
     * 根据组织ID获取岗位列表
     *
     * @param orgId
     * @return
     */
    List<GroupRelation> getListByGroupId(String orgId);


    List<GroupRelation> queryInfoList(QueryFilter queryFilter);

    /**
     * 根据用户ID获取对应的岗位列表
     *
     * @param userId
     * @return
     */
    List<GroupRelation> getListByUserId(String userId);
}
