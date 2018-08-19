package org.minxc.emp.organization.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.minxc.emp.base.core.util.BeanCopierUtils;
import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.organization.api.constant.GroupTypeConstant;
import org.minxc.emp.organization.api.model.GroupType;
import org.minxc.emp.organization.api.model.Group;
import org.minxc.emp.organization.api.model.dto.GroupDto;
import org.minxc.emp.organization.api.service.GroupService;
import org.minxc.emp.organization.core.manager.*;
import org.minxc.emp.organization.core.model.UserModelImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户与组关系的实现类：通过用户找组，通过组找人等
 *
 * @author Administrator
 */
@Service("userGroupService")
@Slf4j
public class DefaultGroupService implements GroupService {


    @Resource
    private UserManager userManager;

    @Resource
    private GroupManager empGroupManager;

    @Resource
    private GroupRelationManager groupRelationManager;

    @Resource
    private UserRoleManager userRoleManager;

    @Resource
    private RoleManager roleManager;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Group> getGroupsByGroupTypeUserId(String groupType, String userId) {
    	List listGroup  = null;
    	
        if (groupType.equals(GroupTypeConstant.ORG.key())) {
        	listGroup = (List) empGroupManager.getByUserId(userId);
        }
        if (groupType.equals(GroupTypeConstant.ROLE.key())) {
        	listGroup = (List) roleManager.getListByUserId(userId);
        }
        if (groupType.equals(GroupTypeConstant.POSITION.key())) {
        	listGroup = (List) groupRelationManager.getListByUserId(userId);
        }
        
        if(listGroup != null) {
        	return (List)BeanCopierUtils.transformList(listGroup, GroupDto.class);
        }
        
        return null;
    }

    @Override
    public Map<String, List<Group>> getAllGroupByAccount(String account) {
    	UserModelImpl user = userManager.getByAccount(account);
    	if(user == null) return Collections.EMPTY_MAP;
    	
    	return getAllGroupByUserId(user.getId());
    }

    
    @Override
    public Map<String, List<Group>> getAllGroupByUserId(String userId) {
        Map<String, List<Group>> listMap = new HashMap<String, List<Group>>();
        
        List<Group> listOrg = (List) empGroupManager.getByUserId(userId);
        if (BeanUtils.isNotEmpty(listOrg)) {
        	List<Group> groupList = (List)BeanCopierUtils.transformList(listOrg, GroupDto.class);
            listMap.put(GroupTypeConstant.ORG.key(), groupList);
        }
        List<Group> listRole = (List) roleManager.getListByUserId(userId);
        if (BeanUtils.isNotEmpty(listRole)) {
        	List<Group> groupList = (List)BeanCopierUtils.transformList(listRole, GroupDto.class);
            listMap.put(GroupTypeConstant.ROLE.key(), groupList);
        }
        List<Group> listOrgRel = (List) groupRelationManager.getListByUserId(userId);
        if (BeanUtils.isNotEmpty(listOrgRel)) {
        	List<Group> groupList = (List)BeanCopierUtils.transformList(listOrgRel, GroupDto.class);
            listMap.put(GroupTypeConstant.POSITION.key(), groupList);
        }
        return listMap;
    }


    /**
     * 根据用户ID获取所有组
     */
    @Override
    public List<Group> getGroupsByUserId(String userId) {
        List<Group> listMap = new ArrayList<Group>();
        List<Group> listOrg = (List) empGroupManager.getByUserId(userId);
        if (BeanUtils.isNotEmpty(listOrg)) {
            listMap.addAll(listOrg);
        }
        List<Group> listRole = (List) roleManager.getListByUserId(userId);
        if (BeanUtils.isNotEmpty(listRole)) {
            listMap.addAll(listRole);
        }
        List<Group> listOrgRel = (List) groupRelationManager.getListByUserId(userId);
        if (BeanUtils.isNotEmpty(listOrgRel)) {
            listMap.addAll(listOrgRel);
        }
        
        //转换成GROUP DTO
        List<Group> groupList = (List)BeanCopierUtils.transformList(listMap, GroupDto.class);
        
        return groupList;
    }

    /**
     * 根据组类别和组ID获取组定义
     */
    @Override
    public Group getById(String groupType, String groupId) {
    	Group group = null;
        if (groupType.equals(GroupTypeConstant.ORG.key())) {
        	group = empGroupManager.get(groupId);
        }
        if (groupType.equals(GroupTypeConstant.ROLE.key())) {
        	group = roleManager.get(groupId);
        }
        if (groupType.equals(GroupTypeConstant.POSITION.key())) {
        	group = groupRelationManager.get(groupId);
        }
        
        group = BeanCopierUtils.transformBean(group, GroupDto.class);
        return group;
    }

    /**
     * 根据组类别和组编码获取组定义
     */
    @Override
    public Group getByCode(String groupType, String code) {
    	Group group = null;
    	
        if (groupType.equals(GroupTypeConstant.ORG.key())) {
        	group = empGroupManager.getByCode(code);
        }
        if (groupType.equals(GroupTypeConstant.ROLE.key())) {
        	group = roleManager.getByAlias(code);
        }
        if (groupType.equals(GroupTypeConstant.POSITION.key())) {
        	group = groupRelationManager.getByCode(code);
        }
        
        group = BeanCopierUtils.transformBean(group, GroupDto.class);
        return group;
    }

    /**
     * 获取所有组类型
     */
    @Override
    public List<GroupType> getGroupTypes() {
        List<GroupType> list = new ArrayList<GroupType>();
        for (GroupTypeConstant e : GroupTypeConstant.values()) {
            GroupType type = new GroupType(e.key(), e.label());
            list.add(type);
        }
        return list;
    }

    @Override
    public Group getMainGroup(String userId) {
        return empGroupManager.getMainGroup(userId);
    }

}
