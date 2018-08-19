package org.minxc.emp.organization.rest.controller;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.query.QueryOperation;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.encrypt.EncryptUtil;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.id.UniqueIdUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import com.github.pagehelper.Page;
import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.base.rest.BaseController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.organization.core.manager.GroupUserManager;
import org.minxc.emp.organization.core.manager.UserManager;
import org.minxc.emp.organization.core.model.GroupUser;
import org.minxc.emp.organization.core.model.UserModelImpl;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * 描述：用户表 控制器类
 * </pre>
 */
@RestController
@RequestMapping("/org/user")
public class UserController extends BaseController<UserModelImpl> {
    @Resource
    UserManager userManager;
    @Resource
    GroupUserManager orgUserManager;

    /**
     * 获取用户下的组织列表
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("listUserOrgJson")
    public PageJson listUserOrgJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        String userId = RequestUtil.getString(request, "userId");
        queryFilter.addFilter("u.id_", userId, QueryOperation.EQUAL);
        Page<UserModelImpl> userList = (Page<UserModelImpl>) userManager.queryOrgUser(queryFilter);
        return new PageJson(userList);
    }

    /**
     * 获取用户下的岗位列表
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("listUserPostJson")
    public PageJson listUserPostJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        String userId = RequestUtil.getString(request, "userId");
        queryFilter.addFilter("orguser.user_id_", userId, QueryOperation.EQUAL);
        Page orgUserList = (Page) userManager.queryUserGroupRel(queryFilter);
        return new PageJson(orgUserList);
    }

    /**
     * 保存用户表信息
     *
     * @param user
     * @throws Exception void
     * @throws
     */
    @RequestMapping("save")
    @CatchError(write2response = true, value = "操作用户失败！")
    public ResultMsg<String> save( @RequestBody UserModelImpl user) throws Exception {
        String resultMsg = null;
        boolean isExist = userManager.isUserExist(user);
        if (isExist) {
            throw new BusinessException("用户在系统中已存在!");
        }

        String id = user.getId();
        if (StringUtil.isEmpty(id)) {
            user.setId(UniqueIdUtil.getSuid());
            String password = EncryptUtil.encryptSha256(user.getPassword());
            user.setPassword(password);
            //添加用户和组织的关系，默认为主关系。
            if (StringUtil.isNotEmpty(user.getGroupId())) {
                GroupUser orgUser = new GroupUser();
                orgUser.setId(UniqueIdUtil.getSuid());
                orgUser.setIsMaster(GroupUser.MASTER_YES);
                orgUser.setGroupId(user.getGroupId());
                orgUser.setUserId(user.getUserId());
                orgUserManager.create(orgUser);
            }
            userManager.create(user);
            resultMsg = "添加用户成功!";
        } else {
            userManager.update(user);
            resultMsg = "更新用户成功";
        }

        return getSuccessResult(user.getId(), resultMsg);
    }


    @RequestMapping("saveUserInfo")
    @CatchError("更新失败")
    public void saveUserInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody UserModelImpl user) throws Exception {
        userManager.update(user);
        writeSuccessData(response, "更新用户成功");
    }

    @RequestMapping("updateUserPsw")
    @CatchError("更新密码失败")
    public void updateUserPsw(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oldPassWorld = RequestUtil.getString(request, "oldPassWorld");
        String newPassword = RequestUtil.getString(request, "newPassword");

        UserModelImpl user = userManager.get(ContextUtil.getCurrentUserId());
        if (!user.getPassword().equals(EncryptUtil.encryptSha256(oldPassWorld))) {
            throw new BusinessException("旧密码输入错误");
        }

        user.setPassword(EncryptUtil.encryptSha256(newPassword));
        userManager.update(user);
        writeSuccessResult(response, "更新密码成功");

    }


    @RequestMapping("getUserByGroupJson")
    public PageJson getUserByGroupJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        String orgId = RequestUtil.getString(request, "orgId");
        String relId = RequestUtil.getString(request, "relId");
        queryFilter.addFilter("orguser.org_id_", orgId, QueryOperation.EQUAL);
        if (StringUtil.isNotEmpty(relId)) {
            queryFilter.addFilter("orguser.rel_id_", relId, QueryOperation.EQUAL);
        }
        Page orgUserList = (Page) orgUserManager.getUserByGroup(queryFilter);
        return new PageJson(orgUserList);
    }

    @Override
    protected String getModelDesc() {
        return "用户";
    }
}
