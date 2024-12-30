package com.example.demo.config;//package com.example.demo.config;
//
//import com.example.demo.commom.TokenUtil;
//import com.example.demo.entity.Userinfo;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created with IntelliJ IDEA.
// * Description:
// * User:龙宝
// * Date:2023-04-02
// * Time:8:02
// */
///*
//登陆拦截器
// */
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
////        HttpSession session = request.getSession(false);
////        if(session != null &&session.getAttribute(AppVariable.USER_SESSION_KEY)!=null) {
////            //用户已登录
////            return true;
////        }
//        //TODO token使用
//        //从请求中获取token
//        String tokenUser = request.getHeader("Authorization");
//        //查看是否存有token
//        Userinfo userinfo = TokenUtil.map.get(tokenUser);
//        if(userinfo != null) {
//            //用户已登录
//            return true;
//        }
//        //调整到登录页面
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json;charset=utf-8");
//        response.sendRedirect("/blog/login.html");
//        return false;
//    }
//}
