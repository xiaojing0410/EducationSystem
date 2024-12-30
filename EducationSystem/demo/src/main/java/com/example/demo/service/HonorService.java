package com.example.demo.service;

import com.example.demo.commom.AvgScore;
import com.example.demo.commom.PasswordUtils;
import com.example.demo.commom.TokenUtil;
import com.example.demo.dto.user.UserUpdateDto;
import com.example.demo.entity.Grades;
import com.example.demo.entity.Honor;
import com.example.demo.entity.Student;
import com.example.demo.mapper.HonorMapper;
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
public class HonorService {
    @Autowired
    private HonorMapper honorMapper;

    // 查询指定学生所有荣誉
    public HashMap<Integer,Object> getHonorByStudentId(Integer userId){
        HashMap<Integer,Object> map = new HashMap<>();
        List<Honor> Ships = honorMapper.getHonorByStudentId(userId);
        map.put(0, Ships);
        return map;
    }
    // 查询指定班级学生所有荣誉
    public HashMap<Integer,Object> getHonorByClassId(Integer class_id){
        HashMap<Integer,Object> map = new HashMap<>();
        List<Honor> Ships = honorMapper.getHonorByClassId(class_id);
        map.put(0, Ships);
        return map;
    }

    // 查询所有学生所有荣誉
    public HashMap<Integer,Object> getAllHonor(){
        HashMap<Integer,Object> map = new HashMap<>();
        List<Honor> Ships = honorMapper.getAllHonor();
        map.put(0, Ships);
        return map;
    }

    // 学生新增荣誉
    public HashMap<Integer,Object> createHonor(Integer userId, String scholarship_name, LocalDateTime award_date, Integer type, Integer state){
        HashMap<Integer,Object> map = new HashMap<>();
        Integer res = honorMapper.createHonor(userId,scholarship_name,award_date,type,state);
        map.put(0,res);
        return map;
    }
    //删除学生的所有荣誉
    public HashMap<Integer, Object> deleteHonorByStudentId(Integer id){
        HashMap<Integer, Object> map = new HashMap<>();
        Integer res = honorMapper.deleteHonorByStudentId(id);
        map.put(0,res);
        return map;
    }

    // 删除指定荣誉
    public HashMap<Integer, Object> deleteOneHonor(Integer id, Integer userId){
        // 需要验证userId是否资格删除
        HashMap<Integer, Object> map = new HashMap<>();
        Honor honor = honorMapper.getHonorById(id);
        if(honor == null){
            map.put(0,0);
        } else if(!honor.getStudent_id().equals(userId)){
            map.put(-1,"参数有误");
        } else {
            Integer res = honorMapper.deleteOneHonor(id);
            map.put(0,res);
        }
        return map;
    }
}
