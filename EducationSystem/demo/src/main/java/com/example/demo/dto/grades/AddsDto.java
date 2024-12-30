package com.example.demo.dto.grades;

import java.util.List;

public class AddsDto {
    private List<Integer> student_id;     // 学号
    private List<Integer> course_id;   // 课程ID列表

    private List<Integer> semester;     // 学期列表
    private List<Double> score;         // 成绩列表

    public List<Integer> getStudent_id() {
        return student_id;
    }

    public void setStudent_id(List<Integer> student_id) {
        this.student_id = student_id;
    }

    public List<Integer> getCourse_id() {
        return course_id;
    }

    public void setCourse_id(List<Integer> course_id) {
        this.course_id = course_id;
    }

    public List<Integer> getSemester() {
        return semester;
    }

    public void setSemester(List<Integer> semester) {
        this.semester = semester;
    }

    public List<Double> getScore() {
        return score;
    }

    public void setScore(List<Double> score) {
        this.score = score;
    }
}