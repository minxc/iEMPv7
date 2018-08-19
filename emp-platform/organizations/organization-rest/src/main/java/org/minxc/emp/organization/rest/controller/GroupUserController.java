package org.minxc.emp.organization.rest.controller;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.query.QueryOperation;
import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.id.UniqueIdUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import com.github.pagehelper.Page;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.organization.core.manager.GroupRelationManager;
import org.minxc.emp.organization.core.manager.GroupUserManager;
import org.minxc.emp.organization.core.manager.UserManager;
import org.minxc.emp.organization.core.model.GroupUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户组织关系 控制器类
 */
@RestController
@RequestMapping("/org/groupUser")
public class GroupUserController extends GenericController {
    @Resource
    GroupUserManager groupUserManager;
    @Resource
    UserManager userManager;
    @Resource
    GroupRelationManager groupRelationManager;

    /**
     * 用户组织关系列表(分页条件查询)数据
     */
    @RequestMapping("groupUserList")
    public PageJson listGroupUserJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String groupId = RequestUtil.getString(request, "groupId");
        String relId = RequestUtil.getString(request, "relId");

        QueryFilter queryFilter = getQueryFilter(request);
        queryFilter.addFilter("org.id_", groupId, QueryOperation.EQUAL);
        if (StringUtil.isNotEmpty(relId)) {
            queryFilter.addFilter("rel.rel_id_", relId, QueryOperation.EQUAL);
        }

        Page<Map> userList = (Page<Map>) groupUserManager.getUserByGroup(queryFilter);
        return new PageJson(userList);
    }


    /**
     * 用户组织关系明细页面
     */
    @RequestMapping("getJson")
    public GroupUser getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        if (StringUtil.isEmpty(id)) {
            return null;
        }

        GroupUser GroupUser = groupUserManager.get(id);
        return GroupUser;
    }


    /**
     * 分配用户岗位
     */
    @RequestMapping("saveGroupUserRel")
    @CatchError
    public void saveGroupUserRel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] relIds = RequestUtil.getStringAryByStr(request, "relIds");
        String[] userId = RequestUtil.getStringAryByStr(request, "userIds");
        String grouId = RequestUtil.getString(request, "groupId");

        groupUserManager.saveGroupUserRel(grouId, userId, relIds);

        writeSuccessResult(response, "分配用户岗位成功");
    }

    /**
     * 批量删除用户组织关系记录
     *
     * @param request
     * @param response
     * @throws Exception void
     * @throws
     */
    @RequestMapping("remove")
    @CatchError
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");
        groupUserManager.removeByIds(aryIds);
        writeSuccessResult(response, "成功删除用户组织岗位关系");
    }

    /**
     * 通过 组织用户删除关系
     */
    @RequestMapping("removeByOrgIdUserId")
    @CatchError
    public void removeByOrgIdUserId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = RequestUtil.getString(request, "userId");
        String orgId = RequestUtil.getString(request, "orgId");

        groupUserManager.removeByOrgIdUserId(orgId, userId);

        writeSuccessResult(response, "成功删除用户组织关系");
    }


    @RequestMapping("setMaster")
    @CatchError
    public void setMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        groupUserManager.setMaster(id);
        writeSuccessResult(response, "设置用户主组织成功!");
    }


    /**
     * 分配用户岗位
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("saveUserRels")
    @CatchError
    public void saveUserPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] relIds = RequestUtil.getStringAryByStr(request, "relIds");
        String[] groupIds = RequestUtil.getStringAryByStr(request, "groupIds");
        String userId = RequestUtil.getString(request, "userId");


        String relId = null;
        for (int i = 0; i < groupIds.length; i++) {
            String orgId = groupIds[i];

            if (BeanUtils.isNotEmpty(relIds)) {
                relId = relIds[i];
            }

            GroupUser GroupUser = groupUserManager.getGroupUser(orgId, userId, relId);
            if (GroupUser == null) {
                GroupUser = groupUserManager.getGroupUser(orgId, userId, "");
                if (GroupUser == null) {
                    GroupUser = new GroupUser();
                    GroupUser.setId(UniqueIdUtil.getSuid());
                    GroupUser.setGroupId(orgId);
                    GroupUser.setUserId(userId);
                    GroupUser.setRelId(relId);
                    GroupUser.setIsMaster(0);
                    groupUserManager.create(GroupUser);
                } else {
                    GroupUser.setRelId(relId);
                    groupUserManager.update(GroupUser);
                }
            }
        }

        writeSuccessResult(response, "分配用户岗位成功");
    }
}
