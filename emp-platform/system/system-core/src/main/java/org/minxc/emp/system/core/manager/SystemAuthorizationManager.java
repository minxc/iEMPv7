package org.minxc.emp.system.core.manager;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.system.api.constant.RightsObjectConstants;
import org.minxc.emp.system.core.model.SysAuthorization;


public interface SystemAuthorizationManager extends Manager<String, SysAuthorization> {

    Set<String> getUserRights(String userId);

    /**
     * 获取授权sql
     *
     * @param userId    用户id
     * @param targetKey 默认为Id_ 关联authorization的 targetId在数据库中的字段名字，
     * @return
     */
    Map<String, Object> getUserRightsSql(RightsObjectConstants rightsObject, String userId, String targetKey);

    List<SysAuthorization> getByTarget(RightsObjectConstants rightsObject, String rightsTarget);


    void createAll(List<SysAuthorization> sysAuthorizationList, String targetId, String targetObject);

}
