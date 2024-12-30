package com.example.demo.mapper;


import com.example.demo.entity.Grades;
import com.example.demo.entity.Honor;
import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:龙宝
 * Date:2023-03-31
 * Time:18:38
 */
@Mapper
public interface StudentMapper {
    int reg(String username, String password);

//    Userinfo login(Userinfo userinfo);
    //根据用户名，查询 登录
    Student getStudentByName(@Param("username") String username);

    Student getStudentById(@Param("id") Integer id);
    List<Student> getStudentByClassId(@Param("id") Integer id);

    List<Student> getStudent();
    //个人信息修改
    int updateStudentInfo(Student student);
    //修改个人state以免多次恶意登录
    //更新state+unfreezingtime
    int upStudentState(Student student);

}
