package com.example.demo.config;//package com.example.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * Created with IntelliJ IDEA.
// * Description:
// * User:龙宝
// * Date:2023-04-02
// * Time:8:06
// */
//@Configuration
//public class AppConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/css/**")
//                .excludePathPatterns("/editor.md/**")
//                .excludePathPatterns("/img/**")
//                .excludePathPatterns("/js/**")
//                .excludePathPatterns("/login.html")
//                .excludePathPatterns("/reg.html")
//                .excludePathPatterns("/blog_add.html")
//                .excludePathPatterns("/blog_edit.html")
//                .excludePathPatterns("/blog_list.html")
//                .excludePathPatterns("/myblog_list.html")
//                .excludePathPatterns("/mycenter_list.html")
//                .excludePathPatterns("/mydraft_list.html")
//                .excludePathPatterns("/blog_content.html")
//                .excludePathPatterns("/find_password.html")
//                .excludePathPatterns("/art/detail")
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/user/getuserbyid")
//                .excludePathPatterns("/art/uprcount")
//                .excludePathPatterns("/comment/querycomments")
//                .excludePathPatterns("/comment/makecomment")
//                .excludePathPatterns("/vote/votecount")
//                .excludePathPatterns("/vote/upvote")
//                .excludePathPatterns("/art/listpage")
//                .excludePathPatterns("/user/findpassword")
//                .excludePathPatterns("/user/reg");
//
//    }
//    //设置访问前缀
////    @Override
////    public void configurePathMatch(PathMatchConfigurer configurer) {
////        configurer.addPathPrefix("/blog",c->true);
////    }
//}
