package com.example.demo.controller;

import com.example.demo.commom.AjaxResult;
import com.example.demo.commom.TokenUtil;
import com.example.demo.dto.course.AddDto;
import com.example.demo.dto.grades.AddsDto;
import com.example.demo.dto.grades.DeleteDto;
import com.example.demo.entity.Teacher;
import com.example.demo.service.CourseService;
import com.example.demo.service.GradesService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;

    // 查询课程信息 - 按学生查 - 按班级查
    @GetMapping("/info")
    public AjaxResult getCourse(HttpServletRequest request, Integer class_id, Integer identity) {
        if(identity == null){
            return AjaxResult.fail("参数有误");
        }
        HashMap<Integer, Object> map = new HashMap<>();
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        if(userId == null){
            return AjaxResult.fail("参数有误");
        }
        if(identity == 1){
            // 学生只能查询自己的课程
            map = courseService.getCourseByStudentId(userId);

        } else if(identity == 2){
            // 教师只能按班级查询课程
            HashMap<Integer, Object> tmp = teacherService.getTeacherById(userId);
            if(tmp.get(0) == null){
                return AjaxResult.fail(tmp.get(-1));
            }
            if(class_id != null){
                map = courseService.getCourseByClassId(class_id);
            }
        }
        if(map.get(0) != null){
            return AjaxResult.success(map.get(0));
        }
        return AjaxResult.fail("参数有误");
    }

    // 新增课程信息
    @PostMapping("/add")
    public AjaxResult addCourse(HttpServletRequest request, @RequestBody AddDto addDto){
        // 判断教师身份
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        if(userId == null){
            return AjaxResult.fail("参数有误");
        }
        HashMap<Integer, Object> mapTeacher = teacherService.getTeacherById(userId);
        if(mapTeacher.get(0) == null){
            return AjaxResult.fail(mapTeacher.get(-1));
        }
        HashMap<Integer, Object> map = courseService.add(addDto.getCourse_name(), addDto.getTeacher_id(), addDto.getClass_id());
        if(map.get(0) != null){
            return AjaxResult.success(map.get(0));
        }
        return AjaxResult.fail("参数有误");
    }
}