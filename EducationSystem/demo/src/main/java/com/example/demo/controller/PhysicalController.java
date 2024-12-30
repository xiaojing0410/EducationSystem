package com.example.demo.controller;

import com.example.demo.commom.AjaxResult;
import com.example.demo.commom.TokenUtil;
import com.example.demo.dto.physical.AddDto;
import com.example.demo.dto.physical.AddsDto;
import com.example.demo.service.PhysicalService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/physical")
public class PhysicalController {
    @Autowired
    private PhysicalService physicalService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    // 查看学生体测成绩 -
    // 1查看指定学生的所有体侧成绩
    // 2查看学生的指定学期的体侧成绩
    // 3查看指定班级的指定学期的体侧表
    @GetMapping("/info")
    public AjaxResult getPhysical(HttpServletRequest request, Integer student_id, Integer class_id, Integer semester, Integer identity){
        if(identity == null){
            return AjaxResult.fail("参数有误");
        }
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        // 学生
        if(identity == 1){
            // 学生只能查看自己的 - 确认学生身份
            HashMap<Integer, Object> tmp = studentService.getStudentByUserId(userId);
            if(tmp.get(0) != null){
                HashMap<Integer, Object> map = new HashMap<>();
                if(semester == null){
                    map = physicalService.getPhysicalByStudentId(userId);
                } else {
                    map = physicalService.getPhysicalByStudentIdAndSemester(userId, semester);
                }
                if(map.get(0) != null){
                    return AjaxResult.success(map.get(0));
                }
            }
            return AjaxResult.fail("参数有误");
        }
        // 教师
        if(identity == 2){
            // 学生只能查看自己的 - 确认教师身份
            // 1查看指定学生的所有体侧成绩
            // 2查看学生的指定学期的体侧成绩
            // 3查看指定班级的指定学期的体侧表
            HashMap<Integer, Object> tmp = teacherService.getTeacherById(userId);
            if(tmp.get(0) != null){
                HashMap<Integer, Object> map = new HashMap<>();
                if(semester != null && class_id != null){
                    map = physicalService.getPhysicalByClassIdAndSemester(class_id, semester);
                }else if(semester != null && student_id != null){
                    map = physicalService.getPhysicalByStudentIdAndSemester(student_id, semester);
                } else if(student_id != null) {
                    map = physicalService.getPhysicalByStudentId(student_id);
                }
                if(map.get(0) != null){
                    return AjaxResult.success(map.get(0));
                }
            }
            return AjaxResult.fail("参数有误");
        }
        return AjaxResult.fail("参数有误");
    }

    // 添加学生成绩
    @PostMapping("/add")
    public AjaxResult addPhysical(HttpServletRequest request, @RequestBody AddDto addDto){
        // 确认教师身份
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        HashMap<Integer, Object> mapTeacher = teacherService.getTeacherById(userId);
        if(mapTeacher.get(-1) != null){
            return AjaxResult.fail(mapTeacher.get(-1));
        }
        if(addDto.getStudent_id() == null
            || addDto.getSemester() == null
            || addDto.getSprint() == null
            || addDto.getRun() == null
            || addDto.getSit_up() == null
            || addDto.getJump() == null
            || addDto.getHeight() == null
            || addDto.getWeight() == null
            || addDto.getStudent_id() < 0
            || addDto.getSemester() < 0
            || addDto.getSprint() < 0
            || addDto.getRun() < 0
            || addDto.getSit_up() < 0
            || addDto.getJump() < 0
            || addDto.getHeight() < 0
            || addDto.getWeight() < 0){
            return AjaxResult.fail("参数有误");
        }
        // 确保学生存在
        HashMap<Integer, Object> tmp = studentService.getStudentByUserId(addDto.getStudent_id());
        if(tmp.get(0) == null){
            return AjaxResult.fail("参数有误 - 该学号有误");
        }

        HashMap<Integer, Object> map = new HashMap<>();
        map = physicalService.add(addDto.getStudent_id(), addDto.getSemester(), addDto.getSprint(),addDto.getRun(),addDto.getSit_up(),addDto.getJump(),addDto.getHeight(),addDto.getWeight());
        if(map.get(0) != null){
            return AjaxResult.success(map.get(0));
        }
        return AjaxResult.fail("参数有误");
    }
    // 批量添加
    @PostMapping("/adds")
    public AjaxResult addsPhysical(HttpServletRequest request, @RequestBody AddsDto addsDto){
        // 确认教师身份
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        HashMap<Integer, Object> mapTeacher = teacherService.getTeacherById(userId);
        if(mapTeacher.get(-1) != null){
            return AjaxResult.fail(mapTeacher.get(-1));
        }
        // 检查传入的数据长度是否一致
        if (addsDto.getStudent_id().size() != addsDto.getSemester().size()
        || addsDto.getSprint().size() != addsDto.getRun().size()
        || addsDto.getJump().size() != addsDto.getSit_up().size()
        || addsDto.getHeight().size() != addsDto.getWeight().size()
        || addsDto.getStudent_id().size() !=  addsDto.getSprint().size()
        || addsDto.getStudent_id().size() !=  addsDto.getJump().size()
        || addsDto.getStudent_id().size() !=  addsDto.getHeight().size()) {
            return AjaxResult.fail("传入的批量数据字段长度不一致");
        }
        HashMap<Integer, Object> map = physicalService.adds(addsDto);
        if(map.get(0) != null){
            return AjaxResult.success(map);
        }
        return AjaxResult.fail("参数有误");
    }
}
