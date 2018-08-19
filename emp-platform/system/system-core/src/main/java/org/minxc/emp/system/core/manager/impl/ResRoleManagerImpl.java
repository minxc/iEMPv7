package org.minxc.emp.system.core.manager.impl;

import org.minxc.emp.base.core.cache.Cache;
import org.minxc.emp.base.db.id.UniqueIdUtil;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.system.core.dao.ResRoleDao;
import org.minxc.emp.system.core.manager.ResRoleManager;
import org.minxc.emp.system.core.model.ResRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <pre>
 * 描述：角色资源分配 处理实现类
 * </pre>
 */
@Service
public class ResRoleManagerImpl extends BaseManager<String, ResRole> implements ResRoleManager {

    @Resource
    private ResRoleDao resRoleDao;
    @Resource
    private Cache cache;

    public final String RESOURCE_URL = "RES_URL_";

    public final String RESOURCE_RES = "SYS_RES_";

    @Override
    public List<ResRole> getAllByRoleId(String roleId) {

        return resRoleDao.getByRoleId(roleId);
    }


    @Override
    public void assignResByRoleSys(String resIds, String systemId, String roleId) {
        resRoleDao.removeByRoleAndSystem(roleId, systemId);

        String[] aryRes = resIds.split(",");
        for (String resId : aryRes) {
            if ("0".equals(resId)) continue;
            ResRole resRole = new ResRole();
            resRole.setId(UniqueIdUtil.getSuid());
            resRole.setRoleId(roleId);
            resRole.setSystemId(systemId);
            resRole.setResId(resId);
            resRoleDao.create(resRole);
        }

    }

    @Override
    public Map<String, Set<String>> getResRoleBySystem(String systemId) {
        String resStr = RESOURCE_RES + systemId;
        if (cache.containKey(resStr)) {
            return (Map<String, Set<String>>) cache.getByKey(resStr);
        }

        List<ResRole> list = resRoleDao.getResRoleBySystemId(systemId);
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();

        for (ResRole res : list) {
            String resAlias = res.getResAlias();
            if (map.containsKey(resAlias)) {
                Set<String> set = map.get(resAlias);
                set.add(res.getRoleAlias());
            } else {
                Set<String> set = new HashSet<String>();
                set.add(res.getRoleAlias());
                map.put(resAlias, set);
            }
        }
        cache.add(resStr, map);
        return map;
    }

    @Override
    public Map<String, Set<String>> getUrlRoleBySystem(String systemId) {
        String urlStr = RESOURCE_URL + systemId;
        if (cache.containKey(urlStr)) {
            return (Map<String, Set<String>>) cache.getByKey(urlStr);
        }

        List<ResRole> list = resRoleDao.getResRoleBySystemId(systemId);
        List<ResRole> urlList = resRoleDao.getUrlRoleBySystemId(systemId);

        urlList.addAll(list);

        Map<String, Set<String>> map = new HashMap<String, Set<String>>();

        for (ResRole res : list) {
            String url = res.getUrl();
            if (map.containsKey(url)) {
                Set<String> set = map.get(url);
                set.add(res.getRoleAlias());
            } else {
                Set<String> set = new HashSet<String>();
                set.add(res.getRoleAlias());
                map.put(url, set);
            }
        }
        //添加到缓存
        cache.add(urlStr, map);
        return map;
    }

    @Override
    public void cleanResCache(String systemId) {
        String urlStr = RESOURCE_URL + systemId;
        String resStr = RESOURCE_RES + systemId;
        cache.delByKey(urlStr);
        cache.delByKey(resStr);
    }


}
