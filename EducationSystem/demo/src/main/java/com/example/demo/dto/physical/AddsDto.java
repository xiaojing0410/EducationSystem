package com.example.demo.dto.physical;

import java.util.List;

public class AddsDto {
    private List<Integer> student_id;     // 学号
    private List<Integer> semester;     // 学期列表
    private List<Double> sprint;   // 短跑50m成绩，10.14-10秒14
    private List<Double> run;   // 长跑800m/1000m成绩，3.54-3分54秒
    private List<Integer> sit_up;   //仰卧起坐/引体向上（个）
    private List<Integer> jump;   // 跳远cm
    private List<Integer> height; // 身高cm
    private List<Integer> weight; //体重kg

    public List<Integer> getStudent_id() {
        return student_id;
    }

    public void setStudent_id(List<Integer> student_id) {
        this.student_id = student_id;
    }

    public List<Integer> getSemester() {
        return semester;
    }

    public void setSemester(List<Integer> semester) {
        this.semester = semester;
    }

    public List<Double> getSprint() {
        return sprint;
    }

    public void setSprint(List<Double> sprint) {
        this.sprint = sprint;
    }

    public List<Double> getRun() {
        return run;
    }

    public void setRun(List<Double> run) {
        this.run = run;
    }

    public List<Integer> getSit_up() {
        return sit_up;
    }

    public void setSit_up(List<Integer> sit_up) {
        this.sit_up = sit_up;
    }

    public List<Integer> getJump() {
        return jump;
    }

    public void setJump(List<Integer> jump) {
        this.jump = jump;
    }

    public List<Integer> getHeight() {
        return height;
    }

    public void setHeight(List<Integer> height) {
        this.height = height;
    }

    public List<Integer> getWeight() {
        return weight;
    }

    public void setWeight(List<Integer> weight) {
        this.weight = weight;
    }
}