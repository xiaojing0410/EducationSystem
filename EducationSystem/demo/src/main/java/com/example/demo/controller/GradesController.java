package com.example.demo.controller;

import com.example.demo.commom.AjaxResult;
import com.example.demo.commom.TokenUtil;
import com.example.demo.dto.grades.AddDto;
import com.example.demo.dto.grades.AddsDto;
import com.example.demo.dto.grades.DeleteDto;
import com.example.demo.entity.Grades;
import com.example.demo.service.GradesService;
import com.example.demo.service.HonorService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradesController {
    @Autowired
    private GradesService gradesService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    // 查询学生的单科成绩 / 学期成绩 所有成绩 - 学生只能查询自己的成绩
    @GetMapping("/student")
    public AjaxResult getGrades(HttpServletRequest request, String courseName, Integer semester){
        // 如果courseName有值，则查询单科; 如果semester有值，则查询指定学期的成绩；否则查询所有成绩
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        if(courseName != null && !courseName.isEmpty()){
            // 查询单科成绩
            HashMap<Integer, Object> map = gradesService.getStudentOneGrade(userId, courseName);
            return AjaxResult.success(map.get(0));
        }
        if(semester != null){
            // 查询指定学期成绩
            HashMap<Integer, Object> map = gradesService.getStudentSemesterGrade(userId, semester);
            return AjaxResult.success(map.get(0));
        }
        // 查询所有成绩
        HashMap<Integer, Object> map = gradesService.getStudentAllGrade(userId);
        return AjaxResult.success(map.get(0));
    }

    // 删除学生成绩 - 删除指定学生成绩 - 按班级删除
    @PostMapping("/delete")
    public AjaxResult deleteGrades(HttpServletRequest request, @RequestBody DeleteDto deleteDto){
        // 确认教师身份
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        HashMap<Integer, Object> mapTeacher = teacherService.getTeacherById(userId);
        if(mapTeacher.get(-1) != null){
            return AjaxResult.fail(mapTeacher.get(-1));
        }
        HashMap<Integer, Object> map = new HashMap<>();
        if(deleteDto.getStudent_id() != null){
            // 删除指定学生的指定科目的成绩
            map = gradesService.deleteByStudentId(deleteDto.getCourse_id(), deleteDto.getStudent_id());

        }else if(deleteDto.getClass_id() != null){
            // 删除指定班级的指定科目的成绩
            map = gradesService.deleteByClassId(deleteDto.getCourse_id(), deleteDto.getClass_id());
        }
        if(map.get(0) != null){
            return AjaxResult.success(map.get(0));
        } else if(map.get(-1) == null){
            map.put(-1,"参数有误");
        }
        return AjaxResult.fail(map.get(-1));
    }
    // 录入学生成绩 - 录入指定学生成绩
    @PostMapping("/add")
    public AjaxResult addGrades(HttpServletRequest request, @RequestBody AddDto addDto){
        // 确认教师身份
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        HashMap<Integer, Object> mapTeacher = teacherService.getTeacherById(userId);
        if(mapTeacher.get(-1) != null){
            return AjaxResult.fail(mapTeacher.get(-1));
        }
        HashMap<Integer, Object> map = new HashMap<>();
        map = gradesService.addGrades(addDto.getStudent_id(), addDto.getCourse_id(), addDto.getSemester(),addDto.getScore());

        if(map.get(0) != null){
            return AjaxResult.success(map.get(0));
        }
        return AjaxResult.fail("参数有误");
    }
    // 录入学生成绩 - 批量录入
    @PostMapping("/adds")
    public AjaxResult addsGrades(HttpServletRequest request, @RequestBody AddsDto addsDto){
        // 确认教师身份
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        HashMap<Integer, Object> mapTeacher = teacherService.getTeacherById(userId);
        if(mapTeacher.get(-1) != null){
            return AjaxResult.fail(mapTeacher.get(-1));
        }
        // 检查传入的数据长度是否一致
        if (addsDto.getCourse_id().size() != addsDto.getStudent_id().size()
            || addsDto.getCourse_id().size() != addsDto.getSemester().size()
            || addsDto.getCourse_id().size() != addsDto.getScore().size()) {
            return AjaxResult.fail("传入的批量数据字段长度不一致");
        }
        HashMap<Integer, Object> map = gradesService.addsGrades(addsDto);
        if(map.get(0) != null){
            return AjaxResult.success(map);
        }
        return AjaxResult.fail("参数有误");
    }

    // 修改学生成绩 - 修改指定学生成绩
    @PostMapping("/update")
    public AjaxResult updateGrades(HttpServletRequest request, @RequestBody AddDto addDto){
        // 参数校验
        if(addDto == null
        || addDto.getStudent_id() == null
        || addDto.getCourse_id() == null
        || addDto.getSemester() == null
        || addDto.getScore() == null
        || addDto.getScore() < 0){
            return AjaxResult.fail("参数有误");
        }
        // 确认教师身份
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        HashMap<Integer, Object> mapTeacher = teacherService.getTeacherById(userId);
        if(mapTeacher.get(-1) != null){
            return AjaxResult.fail(mapTeacher.get(-1));
        }
        HashMap<Integer, Object> map = new HashMap<>();
        map = gradesService.updateGrades(addDto.getStudent_id(), addDto.getCourse_id(), addDto.getSemester(),addDto.getScore());

        if(map.get(0) != null){
            return AjaxResult.success(map);
        }
        return AjaxResult.fail("参数有误");
    }

    // 查看学生的学业预警
    // 预警线：
    // 1、查看该学生的所有成绩 -
    //  1.1总平均分低于60
    //  1.2及格率低于50%
    //  1.3体侧成绩不合格
    // 2、缺课严重 - 暂时没做
    // 3、学科负担过重，即当前学期的课程数量过多 - 暂时不做
    // 4、学术诚信问题 - 有作弊、抄袭等记录 - 暂时不做
    @GetMapping("/warning")
    public AjaxResult warningGrades(HttpServletRequest request, Integer student_id, Integer identity){
        if(identity == null){
            return AjaxResult.fail("参数有误");
        }
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        if(identity == 1){
            // 确认学生身份
            HashMap<Integer, Object> mapStudent = studentService.getStudentByUserId(userId);
            if(mapStudent.get(0) == null){
                return AjaxResult.fail(mapStudent.get(-1));
            }
            student_id = userId;
        } else if(identity == 2){
            // 确认教师身份
            HashMap<Integer, Object> mapTeacher = teacherService.getTeacherById(userId);
            if(mapTeacher.get(-1) != null) {
                return AjaxResult.fail(mapTeacher.get(-1));
            }
        }
        if(student_id == null || student_id <= 0){
            return AjaxResult.fail("参数有误");
        }
        HashMap<Integer, String> map = new HashMap<>();
        // 1、查看该学生的所有成绩
        //  1.1总平均分低于60
        //  1.2不及格率低于50%
        HashMap<Integer,HashMap<String, Object>> map1 = gradesService.getStudentAllGrade2(student_id);
        Integer index = 1;
        if(map1.get(0) != null) {
            HashMap<String, Object> tmp = map1.get(0);
            Double passRate = (Double) tmp.get("passRate");
            Double avgScore = (Double) tmp.get("avgScore");
            if (avgScore < 60) {
                map.put(index, "总平均分低于60");
                index++;
            }
            if (passRate < 0.5) {
                map.put(index, "及格率低于50%");
                index++;
            }
        }
        //  1.3体侧成绩不合格 TODO

        // 2、缺课严重 - 暂时没做
        // 3、学科负担过重，即当前学期的课程数量过多 - 暂时不做
        // 4、学术诚信问题 - 有作弊、抄袭等记录 - 暂时不做
        return AjaxResult.success(map);
    }

    // 生成个性化推荐
    // 查看学生的学业预警
    // 预警线：
    // 1、查看该学生的所有成绩 - 【需要及时联系老师补考或重修了哦】
    //  1.1总平均分低于60
    //  1.2不及格率低于50%
    //  1.3体侧成绩不合格
    // 2、缺课严重 - 暂时没做 - 【不要再上课缺勤了哦，否则会影响毕业哒】
    // 3、学科负担过重，即当前学期的课程数量过多 - 暂时不做 - 【不要再上课缺勤了哦，否则会影响毕业哒】
    // 4、学术诚信问题 - 有作弊、抄袭等记录 - 暂时不做 - 【请注意您的诚信问题哦，需要做一个诚信小宝哦】
    @GetMapping("/suggest")
    public AjaxResult suggestGrades(HttpServletRequest request, Integer student_id, Integer identity){
        if(identity == null){
            return AjaxResult.fail("参数有误");
        }
        Integer userId = TokenUtil.getIdByToken(request.getHeader("token"));
        if(identity == 1){
            // 确认学生身份
            HashMap<Integer, Object> mapStudent = studentService.getStudentByUserId(userId);
            if(mapStudent.get(0) == null){
                return AjaxResult.fail(mapStudent.get(-1));
            }
            student_id = userId;
        } else if(identity == 2){
            // 确认教师身份
            HashMap<Integer, Object> mapTeacher = teacherService.getTeacherById(userId);
            if(mapTeacher.get(-1) != null) {
                return AjaxResult.fail(mapTeacher.get(-1));
            }
        }
        if(student_id == null || student_id <= 0){
            return AjaxResult.fail("参数有误");
        }
        HashMap<Integer, String> map = new HashMap<>();
        Integer index = 1;
        HashMap<Integer,HashMap<String, Object>> map1 = gradesService.getStudentAllGrade2(student_id);
        if(map1.get(0) != null){
            HashMap<String, Object> tmp = map1.get(0);
            Double passRate = (Double) tmp.get("passRate");
            Double avgScore = (Double) tmp.get("avgScore");
            if(avgScore < 60 || passRate < 0.5){
                map.put(index,"需要及时联系老师补考或重修了哦");
                index++;
            }
        }
        return AjaxResult.success(map);
    }


}
