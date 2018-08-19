package org.minxc.emp.organization.core.manager.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.organization.core.dao.GroupRelationDao;
import org.minxc.emp.organization.core.manager.GroupRelationManager;
import org.minxc.emp.organization.core.model.GroupRelation;

/**
 * <pre>
 * 描述：组织关联关系 处理实现类
 * </pre>
 */
@Service
public class GroupRelationManagerImpl extends BaseManager<String, GroupRelation> implements GroupRelationManager {
    @Resource
    GroupRelationDao orgRelDao;

    public GroupRelation getByCode(String code) {
        return this.orgRelDao.getByCode(code);
    }

    public List<GroupRelation> getListByGroupId(String groupId) {
        return this.orgRelDao.getListByGroupId(groupId);
    }

    public List<GroupRelation> queryInfoList(QueryFilter queryFilter) {
        return this.orgRelDao.queryInfoList(queryFilter);
    }

    public List<GroupRelation> getListByUserId(String userId) {
    	if(StringUtil.isEmpty(userId))return Collections.emptyList();
        return this.orgRelDao.getRelListByParam(null, userId);
    }

    public List<GroupRelation> getListByAccount(String account) {
    	if(StringUtil.isEmpty(account))return Collections.emptyList();
        return this.orgRelDao.getRelListByParam(account,null);
    }
}
