package com.example.demo.service;

import com.example.demo.commom.AvgScore;
import com.example.demo.commom.PasswordUtils;
import com.example.demo.commom.TokenUtil;
import com.example.demo.dto.student.StudentDto;
import com.example.demo.dto.user.UserUpdateDto;
import com.example.demo.entity.Grades;
import com.example.demo.entity.Honor;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.TeacherMapper;
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
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    public HashMap<Integer,Object>  reg(String username, String password) {
        HashMap<Integer, Object> map = new HashMap<>();
        //判断用户名是否有人是使用过了
//        Student user = teacherMapper.getStudentByName(username);
//        if(user != null) {
//            map.put(-1,"用户名已存在");
//            return map;
//        }
        // 密码加密
        password = PasswordUtils.encrypt(password);
        //注册
        teacherMapper.reg(username, password);
        map.put(0,0);
        return map;
    }

    public HashMap<Integer, Object> login(Integer id, String password){
        HashMap<Integer, Object> map = new HashMap<>();
        //校验是否可以进行登录操作
        Teacher user = teacherMapper.getTeacherById(id);
        if(user == null || user.getId()<1) {
            map.put(-1,"学号或密码错误");
            return map;
        }
        if(user.getState()>3 && user.getUnfreezingtime() > System.currentTimeMillis()) {
            Long waitTime = (user.getUnfreezingtime()-System.currentTimeMillis())/1000;
            map.put(-1,"\"请等待\"+waitTime+\"s\"");
            return map;
        }
        if(user.getState() > 3 && user.getUnfreezingtime() <= System.currentTimeMillis()) {
            user.setState(0);
            teacherMapper.upTeacherState(user);
        }
        //密码加盐处理过
        if(PasswordUtils.check(password,user.getPassword())) {
            user.setState(0);
            teacherMapper.upTeacherState(user);
            String token = TokenUtil.getToken(user.getId());
            map.put(0,token);
            return map;
        } else {
            //1、先将state修改
            user.setState(user.getState()+1);
            //2、是否修改取消冻结的最新时间
            if(user.getState() > 3) {
                user.setUnfreezingtime(System.currentTimeMillis()+20000);//当前时间往后家100s
            }
            //更新state+unfreezingtime
            teacherMapper.upTeacherState(user);
            map.put(-1,"用户名或密码输入错误");
            return map;
        }
    }
    // 获取教师信息
    public HashMap<Integer,Object>  getTeacherById(Integer id) {
        HashMap<Integer, Object> map = new HashMap<>();
        //判断用户名是否有人是使用过了
        Teacher teacher = teacherMapper.getTeacherById(id);
        if(teacher == null) {
            map.put(-1,"教师用户不存在");
            return map;
        }
        teacher.setPassword("");
        map.put(0,teacher);
        return map;
    }

    // 修改教师信息
    public HashMap<Integer,Object>  updateStudentInfo(UserUpdateDto teacher, Integer id) {
        HashMap<Integer, Object> map = new HashMap<>();
        Teacher user = teacherMapper.getTeacherById(id);
        if(user == null || user.getId() <1 ) {
            map.put(-1,"非法参数");
            return map;
        }

        //开始正式修改
        //密码加盐
        if(!teacher.getPassword().isEmpty()) {
            //如果需要修改密码，需要判断旧密码是否正确
            if(teacher.getOldpassword().isEmpty()){
                map.put(-1,"参数有误");
            }
            if(!PasswordUtils.check(teacher.getOldpassword(),user.getPassword())){
                map.put(-1,"旧密码有误");
                return map;
            }
            user.setPassword(PasswordUtils.encrypt(teacher.getPassword()));
        }
        // 其他信息修改
        if(StringUtils.hasLength(teacher.getEmail())){
            user.setEmail(teacher.getEmail());
        }
        if(StringUtils.hasLength(teacher.getPhone())){
            user.setPhone(teacher.getPhone());
        }
        teacherMapper.updateTeacherInfo(user);
        map.put(0, "修改成功");
        return map;
    }
}
