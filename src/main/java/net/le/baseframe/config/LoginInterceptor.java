package net.le.baseframe.config;

import net.le.baseframe.core.service.ManagerService;
import net.le.baseframe.exception.AppControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private ManagerService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String uri = request.getRequestURI();
        // 模拟登陆成功
        boolean isLogin = true;
//        String token = request.getHeader("set-token");
//        if (token == null || "".equals(token)) {
//            throw new AppControllerException("您还未登录系统，请先进行登陆！");
//        }
//        boolean result = service.checkToken(token);
        if ("/managers/login".equals(uri) || isLogin) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
