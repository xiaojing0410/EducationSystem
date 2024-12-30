package com.example.demo.mapper;


import com.example.demo.entity.Grades;
import com.example.demo.entity.Honor;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
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
public interface TeacherMapper {
    int reg(String username, String password);
    Teacher getTeacherById(@Param("id") Integer id);
    //个人信息修改
    int updateTeacherInfo(Teacher teacher);
    //修改个人state以免多次恶意登录
    //更新state+unfreezingtime
    int upTeacherState(Teacher teacher);

}
