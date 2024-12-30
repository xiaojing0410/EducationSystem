package com.example.demo.mapper;


import com.example.demo.entity.Grades;
import com.example.demo.entity.Honor;
import com.example.demo.entity.Physical;
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
public interface PhysicalMapper {
    Integer add(Integer student_id, Integer semester, Double sprint, Double run, Integer sit_up, Integer jump, Integer height, Integer weight);
    List<Physical> getPhysicalByStudentId(Integer student_id);
    List<Physical> getPhysicalByStudentIdAndSemester(Integer student_id, Integer semester);
    List<Physical> getPhysicalByClassIdAndSemester(Integer class_id, Integer semester);
}
