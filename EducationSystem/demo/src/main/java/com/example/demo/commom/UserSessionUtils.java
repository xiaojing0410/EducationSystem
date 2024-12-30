package com.example.demo.commom;


import com.example.demo.entity.Student;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:龙宝
 * Date:2023-04-02
 * Time:10:41
 */
/*
当前登录用户相关的操作
 */
public class UserSessionUtils {
    /*
    得到当前的登录用户
     */
    public static Student getUser(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if(session != null && session.getAttribute(AppVariable.USER_SESSION_KEY)!=null) {
//            //用户已经登陆了
//            System.out.println((Userinfo) session.getAttribute(AppVariable.USER_SESSION_KEY));
//            return (Userinfo) session.getAttribute(AppVariable.USER_SESSION_KEY);
//        }
//        return null;
        String tokenUser = request.getHeader("Authorization");
        //查看是否存有token
//        Student student = TokenUtil.map.get(tokenUser);
//        return student;
        return  null;
    }
}
