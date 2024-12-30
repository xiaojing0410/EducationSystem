package com.example.demo.mapper;


import com.example.demo.entity.Grades;
import com.example.demo.entity.Honor;
import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:龙宝
 * Date:2023-03-31
 * Time:18:38
 */
@Mapper
public interface HonorMapper {


    // 查询指定学生所有荣誉
    List<Honor> getHonorByStudentId(Integer userId);
    // 查询指定班级学生所有荣誉
    List<Honor> getHonorByClassId(Integer class_id);
    // 查询指定学生所有荣誉
    List<Honor> getAllHonor();

    // 新增奖项
    Integer createHonor(Integer student_id, String honor_name, LocalDateTime award_date, Integer type, Integer state);

    // 删除指定奖项
    Integer deleteOneHonor(Integer id);

    // 删除指定学生的奖项
    Integer deleteHonorByStudentId(Integer id);

    // 根据id查询荣誉
    Honor getHonorById(Integer id);

}
