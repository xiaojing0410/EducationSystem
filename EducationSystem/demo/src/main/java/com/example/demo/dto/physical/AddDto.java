package com.example.demo.dto.physical;

import java.util.List;

public class AddDto {
    private Integer student_id;     // 学号
    private Integer semester;     // 学期列表
    private Double sprint;   // 短跑50m成绩，10.14-10秒14
    private Double run;   // 长跑800m/1000m成绩，3.54-3分54秒
    private Integer sit_up;   //仰卧起坐/引体向上（个）
    private Integer jump;   // 跳远cm
    private Integer height; // 身高cm
    private Integer weight; //体重kg

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Double getSprint() {
        return sprint;
    }

    public void setSprint(Double sprint) {
        this.sprint = sprint;
    }

    public Double getRun() {
        return run;
    }

    public void setRun(Double run) {
        this.run = run;
    }

    public Integer getSit_up() {
        return sit_up;
    }

    public void setSit_up(Integer sit_up) {
        this.sit_up = sit_up;
    }

    public Integer getJump() {
        return jump;
    }

    public void setJump(Integer jump) {
        this.jump = jump;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}