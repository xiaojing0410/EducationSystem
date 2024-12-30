package com.example.demo.service;

import com.example.demo.commom.AvgScore;
import com.example.demo.commom.PasswordUtils;
import com.example.demo.dto.physical.AddsDto;
import com.example.demo.dto.user.UserUpdateDto;
import com.example.demo.entity.Grades;
import com.example.demo.entity.Physical;
import com.example.demo.entity.Student;
import com.example.demo.mapper.PhysicalMapper;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class PhysicalService {
    @Autowired
    private PhysicalMapper physicalMapper;

    // 添加体侧成绩
    public HashMap<Integer, Object> add(Integer student_id, Integer semester, Double sprint, Double run, Integer sit_up, Integer jump, Integer height, Integer weight){
        HashMap<Integer, Object> map = new HashMap<>();
        Integer res = physicalMapper.add(student_id, semester, sprint, run, sit_up, jump, height, weight);
        map.put(0,res);
        return map;
    }

    // 批量添加体侧成绩
    public HashMap<Integer, Object> adds(AddsDto addsDtos){
        HashMap<Integer, Object> map = new HashMap<>();
        // 获取批量数据
        List<Integer> studentIds = addsDtos.getStudent_id();
        List<Integer> semesters = addsDtos.getSemester();
        List<Double> sprint = addsDtos.getSprint();
        List<Double> run = addsDtos.getRun();
        List<Integer> sit_up = addsDtos.getSit_up();
        List<Integer> jump = addsDtos.getJump();
        List<Integer> height = addsDtos.getHeight();
        List<Integer> weight = addsDtos.getWeight();
        int count = 0;
        for(int i = 0;i<studentIds.size();i++){
            int res = physicalMapper.add(studentIds.get(i), semesters.get(i), sprint.get(i),run.get(i),sit_up.get(i),jump.get(i), height.get(i), weight.get(i));
            count += res;
        }
        HashMap<String, Integer> tmp = new HashMap<>();
        tmp.put("成功",count);
        tmp.put("失败",studentIds.size()-count);
        map.put(0,tmp);
        return map;
    }

    // 查看指定学生的所有体侧成绩
    public HashMap<Integer, Object> getPhysicalByStudentId(Integer student_id){
        HashMap<Integer, Object> map = new HashMap<>();
        List<Physical> res = physicalMapper.getPhysicalByStudentId(student_id);
        map.put(0,res);
        return map;
    }
    // 查看学生的指定学期的体侧成绩
    public HashMap<Integer, Object> getPhysicalByStudentIdAndSemester(Integer student_id, Integer semester){
        HashMap<Integer, Object> map = new HashMap<>();
        List<Physical> res = physicalMapper.getPhysicalByStudentIdAndSemester(student_id, semester);
        map.put(0,res);
        return map;
    }

    // 查看指定班级的指定学期的体侧表
    public HashMap<Integer, Object> getPhysicalByClassIdAndSemester(Integer class_id, Integer semester){
        HashMap<Integer, Object> map = new HashMap<>();
        List<Physical> res = physicalMapper.getPhysicalByClassIdAndSemester(class_id, semester);
        map.put(0,res);
        return map;
    }
}
