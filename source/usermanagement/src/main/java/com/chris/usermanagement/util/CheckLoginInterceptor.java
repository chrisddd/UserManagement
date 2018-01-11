package com.chris.usermanagement.util;

import com.chris.usermanagement.model.Role;
import com.chris.usermanagement.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jodd.util.MimeTypes;
import jodd.util.StringPool;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Check login interceptor.
 *
 * @author did
 * @date 2017年11月29日
 */
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            return checkLogin(request, response, handlerMethod);
        }
        return true;
    }

    private boolean checkLogin(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod)
            throws IOException {
        User user = (User) request.getSession().getAttribute(Constants.LOGIN_SESSION_VALUE);

        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的Permission注解
        Permission rl = method.getAnnotation(Permission.class);
        if (rl == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
//        if (rl.authPrivileges().length > 0) {
        if (ArrayUtils.isNotEmpty(rl.roles())) {

            if (null == user) {
                logonFailureResponse(response);
                return false;
            }

            // 如果权限配置不为空, 则取出配置值
            String[] authorities = rl.roles();
            Set<String> authSet = new HashSet<>();
            for (String authority : authorities) {
                // 将权限加入一个set集合中
                authSet.add(authority);
            }
            // 这里我为了方便是直接参数传入权限, 在实际操作中应该是从参数中获取用户Id
            // 到数据库权限表中查询用户拥有的权限集合, 与set集合中的权限进行对比完成权限校验
            List<Role> roles = user.getRoles();
            for (Role role : roles) {
                if (authSet.contains(role.getRoleEnname())) {
                    // 校验通过返回true, 否则拦截请求
                    return true;
                }
            }
        }

        failedResponse(response);
        return false;
    }

    private void failedResponse(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding(StringPool.UTF_8);
        response.setContentType(MimeTypes.MIME_APPLICATION_JSON);
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResult(false);
        resultResponse.setCode("101");
        resultResponse.setMessage("permission denied");
        ObjectMapper om = new ObjectMapper();
        response.getWriter().write(om.writeValueAsString(resultResponse));
    }

    private void logonFailureResponse(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding(StringPool.UTF_8);
        response.setContentType(MimeTypes.MIME_APPLICATION_JSON);
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResult(false);
        resultResponse.setCode("401");
        resultResponse.setMessage("尚未登录或session失效");
        ObjectMapper om = new ObjectMapper();
        response.getWriter().write(om.writeValueAsString(resultResponse));
    }
}
