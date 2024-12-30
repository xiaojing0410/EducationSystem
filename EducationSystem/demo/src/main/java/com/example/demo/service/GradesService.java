package com.example.demo.service;

import com.example.demo.commom.AvgScore;
import com.example.demo.commom.PasswordUtils;
import com.example.demo.commom.TokenUtil;
import com.example.demo.dto.grades.AddDto;
import com.example.demo.dto.grades.AddsDto;
import com.example.demo.dto.user.UserUpdateDto;
import com.example.demo.entity.Grades;
import com.example.demo.entity.Honor;
import com.example.demo.entity.Student;
import com.example.demo.mapper.GradesMapper;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
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
public class GradesService {
    @Autowired
    private GradesMapper gradesMapper;

    // 查询学生单科成绩
    public HashMap<Integer,Object> getStudentOneGrade(Integer userId, String courseName){
        HashMap<Integer,Object> map = new HashMap<>();
        List<Grades> grades = gradesMapper.getStudentOneScore(userId, courseName);
        Double avgScore = AvgScore.avgScore(grades);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("grades", grades);
        resultMap.put("avgScore", avgScore);
        map.put(0, resultMap);
        return map;
    }

    // 查询学生某学期成绩
    public HashMap<Integer,Object> getStudentSemesterGrade(Integer userId, Integer semester){
        HashMap<Integer,Object> map = new HashMap<>();
        List<Grades> grades = gradesMapper.getStudentSemesterScore(userId, semester);
        Double avgScore = AvgScore.avgScore(grades);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("grades", grades);
        resultMap.put("avgScore", avgScore);
        map.put(0, resultMap);
        return map;
    }

    // 查询学生所有成绩
    public HashMap<Integer,Object> getStudentAllGrade(Integer userId){
        HashMap<Integer,Object> map = new HashMap<>();
        List<Grades> grades = gradesMapper.getStudentAllScore(userId);
        Double avgScore = AvgScore.avgScore(grades);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("grades", grades);
        resultMap.put("avgScore", avgScore);
        map.put(0, resultMap);
        return map;
    }

    // 查询学生所有成绩 - 并给出平均分-及格率
    public HashMap<Integer,HashMap<String, Object>> getStudentAllGrade2(Integer userId){
        HashMap<Integer,HashMap<String, Object>> map = new HashMap<>();
        List<Grades> grades = gradesMapper.getStudentAllScore(userId);
        Double avgScore = AvgScore.avgScore(grades);
        double count = 0;
        for(int i = 0;i<grades.size();i++){
            if(grades.get(i).getScore() > 60){
                count++;
            }
        }
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("avgScore", avgScore);
        resultMap.put("passRate",count/grades.size());
        map.put(0, resultMap);
        return map;
    }

    // 删除学生指定科目成绩
    public HashMap<Integer,Object>  deleteByStudentId(Integer course_id, Integer user_id) {
        HashMap<Integer, Object> map = new HashMap<>();
        Integer res = gradesMapper.deleteByStudentId(course_id, user_id);
        map.put(0,res);
        return map;
    }

    // 删除某班级指定科目成绩
    public HashMap<Integer,Object>  deleteByClassId(Integer course_id, Integer class_id) {
        HashMap<Integer, Object> map = new HashMap<>();
        Integer res = gradesMapper.deleteByClassId(course_id, class_id);
        map.put(0,res);
        return map;
    }

    // 添加学生成绩
    public HashMap<Integer, Object> addGrades(Integer id,Integer course_id,Integer semester, Double score){
        HashMap<Integer, Object> map = new HashMap<>();
        Integer res = gradesMapper.addGrades(id, course_id, semester, score);
        map.put(0,res);
        return map;
    }

    // 批量添加学生成绩
    public HashMap<Integer, Object> addsGrades(AddsDto addsDtos){
        HashMap<Integer, Object> map = new HashMap<>();
        // 获取批量数据
        List<Integer> courseIds = addsDtos.getCourse_id();
        List<Integer> studentIds = addsDtos.getStudent_id();
        List<Integer> semesters = addsDtos.getSemester();
        List<Double> scores = addsDtos.getScore();
        int count = 0;
        for(int i = 0;i<courseIds.size();i++){
            int res = gradesMapper.addGrades(studentIds.get(i), courseIds.get(i), semesters.get(i), scores.get(i));
            count += res;
        }
        HashMap<String, Integer> tmp = new HashMap<>();
        tmp.put("成功",count);
        tmp.put("失败",courseIds.size()-count);
        map.put(0,tmp);
        return map;
    }

    // 修改学生单科成绩
    public HashMap<Integer, Object> updateGrades(Integer id,Integer course_id,Integer semester, Double score){
        HashMap<Integer, Object> map = new HashMap<>();
        Integer res = gradesMapper.updateGrades(id, course_id, semester, score);
        map.put(0,res);
        return map;
    }
}
