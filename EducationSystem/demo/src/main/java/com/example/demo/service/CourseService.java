package com.example.demo.service;

import com.example.demo.commom.AvgScore;
import com.example.demo.dto.grades.AddsDto;
import com.example.demo.entity.Course;
import com.example.demo.entity.Grades;
import com.example.demo.entity.Student;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.GradesMapper;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:龙宝
 * Date:2023-03-31
 * Time:18:37
 */
@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentMapper studentMapper;

    // 新增课程
    public HashMap<Integer,Object> add(String course_name, Integer teacher_id, Integer class_id){
        HashMap<Integer,Object> map = new HashMap<>();
        Integer res = courseMapper.add(course_name, teacher_id, class_id);
        map.put(0, res);
        return map;
    }

    // 查询指定学生的课程
    public HashMap<Integer,Object> getCourseByStudentId(Integer student_id){
        HashMap<Integer,Object> map = new HashMap<>();
        // 查看当前学生的class_id
        Student student = studentMapper.getStudentById(student_id);
        if(student == null){
            map.put(-1,"用户有误");
            return map;
        }
        List<Course> res = courseMapper.getCourseByClassId(student.getClass_id());
        map.put(0, res);
        return map;
    }

    // 查询指定班级的课程
    public HashMap<Integer,Object> getCourseByClassId(Integer class_id){
        HashMap<Integer,Object> map = new HashMap<>();
        List<Course> res = courseMapper.getCourseByClassId(class_id);
        map.put(0, res);
        return map;
    }
}
