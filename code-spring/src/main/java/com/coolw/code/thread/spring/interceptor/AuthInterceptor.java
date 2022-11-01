package com.coolw.code.thread.spring.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户认证拦截器
 *   preHandle:目标方法执行前执行
 *   postHandle:目标方法执行后执行
 *   afterCompletion:请求完成时执行
 * 场景:权限、日志、统计等等
 * 
 * 步骤
 *   1.继承HandlerInterceptorAdapter类定义拦截器
 *   2.将该拦截器注册到spring容器（WebConfig）
 * 
 * @author coolw
 * @date 2022/10/12 9:42
 * @since 1.0
 */
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("用户认证拦截器-目标方法执行前处理");
        String uri = request.getRequestURI();
        return checkAuth(uri);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("用户认证拦截器-目标方法执行后处理");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("用户认证拦截器-请求完成处理");
    }

    private boolean checkAuth(String uri) {
        // TODO 用户认证处理
        return true;
    } 
}
