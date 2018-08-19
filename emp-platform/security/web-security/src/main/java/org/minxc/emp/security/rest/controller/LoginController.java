package org.minxc.emp.security.rest.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.organization.api.service.UserService;
import org.minxc.emp.security.constant.PlatFormStatusCode;
import org.minxc.emp.security.login.SecurityUtil;

@RestController
public class LoginController extends GenericController {
    @Resource
    UserService userService;
    @Resource
    SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();

    @RequestMapping(value = "login/valid")
    @CatchError
    public ResultMsg login(HttpServletRequest request, HttpServletResponse response) {
        String account = RequestUtil.getString(request, "account");
        String password = RequestUtil.getString(request, "password");
        if (StringUtil.isEmpty(account)) {
            throw new BusinessException("账户不能为空", PlatFormStatusCode.LOGIN_ERROR);
        }
        if (StringUtil.isEmpty(password)) {
            throw new BusinessException("密码不能为空", PlatFormStatusCode.LOGIN_ERROR);
        }

        try {
            Authentication auth = SecurityUtil.login(request, account, password, false);
            sessionStrategy.onAuthentication(auth, request, response);
            //执行记住密码动作。
            SecurityUtil.writeRememberMeCookie(request, response, account, password);

            wiriteToken(request, response);
            return getSuccessResult("登录成功！");
        } catch (BadCredentialsException e) {
            throw new BusinessException("账号或密码错误", PlatFormStatusCode.LOGIN_ERROR);
        } catch (DisabledException e) {
            throw new BusinessException("帐号已禁用", PlatFormStatusCode.LOGIN_ERROR);
        } catch (LockedException e) {
            throw new BusinessException("帐号已锁定", PlatFormStatusCode.LOGIN_ERROR);
        } catch (AccountExpiredException e) {
            throw new BusinessException("帐号已过期", PlatFormStatusCode.LOGIN_ERROR);
        } catch (Exception ex) {
        	ex.printStackTrace();
            throw new BusinessException(PlatFormStatusCode.LOGIN_ERROR, ex);
        }
    }


    protected static final String REQUEST_ATTRIBUTE_NAME = "_csrf";

    private void wiriteToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CsrfToken token = (CsrfToken) request.getAttribute(REQUEST_ATTRIBUTE_NAME);

        if (token != null) {
            Cookie cookie = new Cookie("XSRF-TOKEN", token.getToken());
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
    }
}