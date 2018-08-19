package org.minxc.emp.security.authentication;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.minxc.emp.security.constant.PlatformSecurityConstants;
import org.minxc.emp.security.login.model.LoginUser;


public class AccessDecisionManagerImpl implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        if (configAttributes.contains(PlatformSecurityConstants.ROLE_CONFIG_ANONYMOUS)) {
            return;
        }

        // 登陆访问
        if (authentication == null) {
            throw new AccessDeniedException("没有登录系统");
        }
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            throw new AccessDeniedException("登录对象为空");
        }

        if (!(principal instanceof LoginUser)) {
            throw new AccessDeniedException("登录对象必须为LoginUser实现类");
        }
        UserDetails user = (UserDetails) principal;
        // 获取当前用户的角色。
        Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>) user.getAuthorities();
        // 超级访问
        if (roles.contains(PlatformSecurityConstants.ROLE_GRANT_SUPER)) {
            return;
        }
        // 公开访问
        if (configAttributes.contains(PlatformSecurityConstants.ROLE_CONFIG_PUBLIC)) {
            return;
        }
        // 授权访问
        // 遍历当前用户的角色，如果当前页面对应的角色包含当前用户的某个角色则有权限访问。
        for (GrantedAuthority hadRole : roles) {
            if (configAttributes.contains(new SecurityConfig(hadRole.getAuthority()))) {
                return;
            }
        }
        throw new AccessDeniedException("对不起,你没有访问该页面的权限!");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
