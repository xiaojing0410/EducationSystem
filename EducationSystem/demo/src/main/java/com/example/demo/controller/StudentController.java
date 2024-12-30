package com.example.demo.controller;

import com.example.demo.commom.AjaxResult;
import com.example.demo.commom.PasswordUtils;
import com.example.demo.commom.TokenUtil;
import com.example.demo.commom.UserSessionUtils;
import com.example.demo.dto.student.HonorDto;
import com.example.demo.dto.student.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    // 查看学生基础信息 - 查看指定学生 - 按班级查看 - 查看所有
    @GetMapping("/info")
    public AjaxResult getStudentInfo(HttpServletRequest request, Integer studentId, Integer classId){
        // 确认教师身份
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        HashMap<Integer, Object> mapTeacher = teacherService.getTeacherById(userId);
        if(mapTeacher.get(-1) != null){
            return AjaxResult.fail(mapTeacher.get(-1));
        }
        HashMap<Integer, Object> map = new HashMap<>();
        // 按不同要求获取信息
        if(studentId != null){
            map = studentService.getStudentByUserId(studentId);
        } else if(classId != null){
            map = studentService.getStudentByClassId(classId);
        } else {
            map = studentService.getStudent();
        }
        if(map.get(0) == null){
            return AjaxResult.fail(map.get(-1));
        }
        return AjaxResult.success(map.get(0));
    }


}


































