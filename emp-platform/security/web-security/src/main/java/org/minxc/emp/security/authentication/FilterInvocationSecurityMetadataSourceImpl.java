package org.minxc.emp.security.authentication;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.minxc.emp.security.IngoreChecker;
import org.minxc.emp.security.util.SubSystemUtil;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.security.constant.PlatformSecurityConstants;
import org.minxc.emp.system.api.service.SystemResourceService;

/**
 * 根据当前的URL获取他上面分配的角色列表
 */
public class FilterInvocationSecurityMetadataSourceImpl extends IngoreChecker implements FilterInvocationSecurityMetadataSource {

    @Resource
    private SystemResourceService systemResourceService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Collection<ConfigAttribute> configAttribute = new HashSet<ConfigAttribute>();

        FilterInvocation filterInvocation = ((FilterInvocation) object);
        HttpServletRequest request = filterInvocation.getRequest();

        String url = request.getRequestURI();
        url = removeCtx(url, request.getContextPath());

        if (isIngores(url)) {
            configAttribute.add(PlatformSecurityConstants.ROLE_CONFIG_ANONYMOUS);
            return configAttribute;
        }

        String systemId = SubSystemUtil.getSystemId(request);
        Map<String, Set<String>> urlRoleMap = systemResourceService.getUrlRoleBySystem(systemId);
        //根据当前的URL获取资源对应的角色。
        if (urlRoleMap.containsKey(url)) {
            Set<String> urlSet = urlRoleMap.get(url);
            for (String role : urlSet) {
                configAttribute.add(new SecurityConfig(role));
            }
        } else {
            configAttribute.add(PlatformSecurityConstants.ROLE_CONFIG_PUBLIC);
        }
        return configAttribute;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }


    /**
     * 获取当前URL
     *
     * @param url
     * @param ctxPath
     * @return
     */
    private static String removeCtx(String url, String ctxPath) {
        url = url.trim();
        if (StringUtil.isEmpty(ctxPath)) return url;
        if (StringUtil.isEmpty(url)) return "";
        if (url.startsWith(ctxPath)) {
            url = url.replaceFirst(ctxPath, "");
        }
        return url;
    }

}
