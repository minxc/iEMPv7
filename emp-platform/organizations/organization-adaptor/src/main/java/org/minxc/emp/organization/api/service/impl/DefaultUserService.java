package org.minxc.emp.organization.api.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.minxc.emp.base.core.util.BeanCopierUtils;
import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.organization.api.constant.GroupTypeConstant;
import org.minxc.emp.organization.api.model.User;
import org.minxc.emp.organization.api.model.UserRole;
import org.minxc.emp.organization.api.model.dto.UserDto;
import org.minxc.emp.organization.api.model.dto.UserRoleDto;
import org.minxc.emp.organization.api.service.UserService;
//import org.minxc.emp.organization.core.manager.EmpGroupManager;
import org.minxc.emp.organization.core.manager.UserManager;
import org.minxc.emp.organization.core.manager.UserRoleManager;

@Slf4j
@Service
public class DefaultUserService implements UserService {
    @Resource
   private UserManager userManager;
//    @Resource
//    private EmpGroupManager empGroupManager;
    @Resource
    private UserRoleManager userRoleManager;

    @Override
    public User getUserById(String userId) {
    	User user = userManager.get(userId);
        return BeanCopierUtils.transformBean(user, UserDto.class);
    }

    @Override
    public User getUserByAccount(String account) {
    	User user =  userManager.getByAccount(account);
    	return BeanCopierUtils.transformBean(user, UserDto.class);
    }

    @Override
    public List<User> getUserListByGroup(String groupType, String groupId) {
        //此处可以根据不同的groupType去调用真实的实现：如角色下的人，组织下的人

    	List<User> userList  = null;
        if (groupType.equals(GroupTypeConstant.ORG.key())) {
        	userList = (List) userManager.getUserListByOrgId(groupId);
        }

        if (groupType.equals(GroupTypeConstant.ROLE.key())) {
        	userList =  (List) userManager.getUserListByRoleId(groupId);
        }
        if (groupType.equals(GroupTypeConstant.POSITION.key())) {
        	userList =  (List) userManager.getListByRelId(groupId);
        }

        if(BeanUtils.isNotEmpty(userList)) {
        	return (List)BeanCopierUtils.transformList(userList, UserDto.class);
        }

        return Collections.emptyList();
    }

	@Override
	public List<UserRole> getUserRole(String userId) {
		List<UserRole> userRole = (List)userRoleManager.getListByUserId(userId);

		return  (List)BeanCopierUtils.transformList(userRole, UserRoleDto.class);
	}


}
