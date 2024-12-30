package com.example.demo.controller;

import com.example.demo.commom.AjaxResult;
import com.example.demo.commom.TokenUtil;
import com.example.demo.dto.student.StudentDto;
import com.example.demo.entity.Teacher;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

}
