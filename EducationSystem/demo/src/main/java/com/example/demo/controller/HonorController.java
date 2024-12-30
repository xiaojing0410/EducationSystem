package com.example.demo.controller;

import com.example.demo.commom.AjaxResult;
import com.example.demo.commom.TokenUtil;
import com.example.demo.dto.honor.Delete;
import com.example.demo.dto.student.HonorDto;
import com.example.demo.service.HonorService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/honor")
public class HonorController {
    @Autowired
    private HonorService honorService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    // 查询学生的荣誉奖项
    @GetMapping("/student")
    public AjaxResult getHonor(HttpServletRequest request){
        // 通过学生的学号来查询
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        if(userId == null){
            return AjaxResult.fail("参数异常",-1);
        }
        HashMap<Integer, Object> map = honorService.getHonorByStudentId(userId);
        return AjaxResult.success(map.get(0));
    }

    // 学生申请荣誉奖项 - 暂时不做审核，申请就直接添加成功
    @PostMapping("/apply")
    public AjaxResult applyHonor(HttpServletRequest request, @RequestBody HonorDto honorDto){
        // 通过学生的学号来查询
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        if(userId == null){
            return AjaxResult.fail("token有误",-1);
        }
        if(!StringUtils.hasLength(honorDto.getHonor_name())){
            return AjaxResult.fail("荣誉名有误");
        }
        if(honorDto.getAward_date() == null){
            honorDto.setAward_date(LocalDateTime.now());
        }
        if(honorDto.getType() == null || honorDto.getType() > 2 || honorDto.getType() <0){
            honorDto.setType(0);
        }
        HashMap<Integer, Object> map = honorService.createHonor(userId,honorDto.getHonor_name(),honorDto.getAward_date(),honorDto.getType(),1);
        return AjaxResult.success(0,map.get(0));
    }

    // 学生删除自己的荣誉项 - 暂时不做审核，申请就直接删除成功 TODO 调试 400
    @PostMapping("/delete")
    public AjaxResult deleteHonor(HttpServletRequest request, @RequestBody Delete delete){
        Integer id = delete.getId();
        // 通过学生的学号来查询
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        if(userId == null){
            return AjaxResult.fail("参数异常",-1);
        }
        HashMap<Integer, Object> map = new HashMap<>();
        // id存在则删除指定，否则删除全部
        if(id == null) {
            // 删除全部
            map = honorService.deleteHonorByStudentId(userId);
        }else if(id < 0){
            return AjaxResult.fail("参数有误",-1);
        } else {
            // 删除指定荣誉
            map = honorService.deleteOneHonor(id,userId);
        }
        if(map.get(0) != null){
            return AjaxResult.success(map.get(0));
        }
        return AjaxResult.fail("参数有误",-1);
    }


    // 教师 - 查看学生荣誉 - 查看指定学生 - 按班级查看 - 查看所有
    @GetMapping("/students")
    public AjaxResult getStudentsHonor(HttpServletRequest request, Integer studentId, Integer classId){
        // 确认教师身份
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        HashMap<Integer, Object> mapTeacher = teacherService.getTeacherById(userId);
        if(mapTeacher.get(-1) != null){
            return AjaxResult.fail(mapTeacher.get(-1));
        }
        HashMap<Integer, Object> map = new HashMap<>();
        // 按不同要求获取信息
        if(studentId != null){
            map = honorService.getHonorByStudentId(studentId);
        } else if(classId != null){
            map = honorService.getHonorByClassId(classId);
        } else {
            map = honorService.getAllHonor();
        }
        if(map.get(0) == null){
            return AjaxResult.fail(map.get(-1));
        }
        return AjaxResult.success(map.get(0));
    }
}
