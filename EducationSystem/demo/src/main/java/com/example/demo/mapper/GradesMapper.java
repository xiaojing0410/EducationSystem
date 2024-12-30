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
public interface GradesMapper {
    // 查询学生单科成绩
    List<Grades> getStudentOneScore(Integer userId, String courseName);

    // 查询学生学期成绩
    List<Grades> getStudentSemesterScore(Integer userId, Integer semester);

    // 查询学生所有成绩
    List<Grades> getStudentAllScore(Integer userId);

   // 删除指定学生的指定科目成绩
    Integer deleteByStudentId(Integer course_id, Integer user_id);

    // 删除指定班级的指定科目成绩
    Integer deleteByClassId(Integer course_id, Integer class_id);

    // 添加学生成绩
    Integer addGrades(Integer id,Integer course_id,Integer semester, Double score);

    // 修改学生单科成绩
    Integer updateGrades(Integer id, Integer course_id,Integer semester, Double score);

}
