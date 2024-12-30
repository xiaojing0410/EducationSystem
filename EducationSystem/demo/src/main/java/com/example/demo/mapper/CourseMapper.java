package com.example.demo.mapper;


import com.example.demo.entity.Course;
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
public interface CourseMapper {
    Integer add(String course_name, Integer teacher_id, Integer class_id);


    List<Course> getCourseByClassId(Integer class_id);
}
