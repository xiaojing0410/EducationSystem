package com.example.demo.controller;

import com.example.demo.commom.*;
import com.example.demo.dto.student.StudentDto;
import com.example.demo.dto.user.LoginDto;
import com.example.demo.dto.user.RegDto;
import com.example.demo.dto.user.UserUpdateDto;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @PostMapping("/reg")
    public AjaxResult reg(@RequestBody RegDto regDto){
        String username = regDto.getUsername();
        String password = regDto.getPassword();
        Integer identity = regDto.getIdentity();
        if(!StringUtils.hasLength(username)
        || !StringUtils.hasLength(password)
        || password.length() < 3
        || password.length() > 20
        || identity == null) {
            return AjaxResult.fail("参数错误",-1);
        }
        // 学生
        if(identity == 1){
            HashMap<Integer, Object> map = new HashMap<>();
            map = studentService.reg(username,password);
            if(map.get(-1) != null) {
                return AjaxResult.fail(map.get(-1));
            }
            return AjaxResult.success(0);
        }
        // 教师 TODO:
        if(identity == 2){
            HashMap<Integer, Object> map = new HashMap<>();
            map = teacherService.reg(username,password);
            if(map.get(-1) != null) {
                return AjaxResult.fail(map.get(-1));
            }
            return AjaxResult.success(0);
        }
        return AjaxResult.fail("参数错误",-1);
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginDto loginDto){
        Integer id = loginDto.getId();
        String password = loginDto.getPassword();
        Integer identity = loginDto.getIdentity();
        //1、判空
        if( id == null
        || !StringUtils.hasLength(password)
        ||password.length() > 10
        || identity == null) {
            return AjaxResult.fail("参数错误",-1);
        }
        // 学生
        if(identity == 1){
            HashMap<Integer, Object> map = studentService.login(id,password);
            if(map.get(-1) != null) {
                return AjaxResult.fail(map.get(-1), -1);
            } else {
                return AjaxResult.success(map.get(0));
            }
        }
        // 教师
        if(identity == 2){
            HashMap<Integer, Object> map = teacherService.login(id,password);
            if(map.get(-1) != null) {
                return AjaxResult.fail( map.get(-1), -1);
            } else {
                return AjaxResult.success(map.get(0));
            }
        }
        return AjaxResult.fail("学号或密码输入错误",-1);
    }


    @PostMapping("/logout")
    public AjaxResult logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        TokenUtil.deleteToken(token);
        return AjaxResult.success(0);
    }
    @GetMapping("/getUserInfo")
    public AjaxResult getUserById(HttpServletRequest request,Integer identity) {
        String token = request.getHeader("token");
        Integer id = TokenUtil.getIdByToken(token);
        if(id == null || id <= 0){
            return AjaxResult.fail("token不存在",-1);
        }
        // 学生
        if(identity == 1){
            HashMap<Integer, Object> map = studentService.getStudentByUserId(id);
            if(map.get(-1) != null){
                return AjaxResult.fail(map.get(-1));
            }
            return AjaxResult.success(map.get(0));
        }
        //教师
        if(identity == 2){
            HashMap<Integer, Object> map = teacherService.getTeacherById(id);
            if(map.get(-1) != null){
                return AjaxResult.fail(map.get(-1));
            }
            return AjaxResult.success(map.get(0));
        }
        return AjaxResult.fail("参数错误",-1);
    }

    //修改个人信息 - 专业班级学号等不得由学生自己删除
    @PostMapping("/update")
    public AjaxResult updateStudentInfo(@RequestBody UserUpdateDto userUpdateDto, HttpServletRequest request) {
        //获取当前用户信息
        String token = request.getHeader("token");
        Integer userId = TokenUtil.getIdByToken(token);
        HashMap<Integer, Object> map = new HashMap<>();
        if(userUpdateDto.getIdentity() == 1){
            // 学生
            map = studentService.updateStudentInfo(userUpdateDto, userId);
        } else if (userUpdateDto.getIdentity() == 2) {
            // 教师
            map = teacherService.updateStudentInfo(userUpdateDto, userId);
        }
        if(map.get(-1) != null){
            return AjaxResult.fail("参数错误",-1);
        }
        return AjaxResult.success(map.get(0));
    }

}


































