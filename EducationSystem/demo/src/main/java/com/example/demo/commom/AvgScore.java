package com.example.demo.commom;

import com.example.demo.entity.Grades;

import java.util.List;

public class AvgScore {
    public static Double avgScore(List<Grades> grades){
        Double scores = 0.0;
        for(Grades grade : grades){
            scores += grade.getScore();
        }
        return scores / grades.size();
    }
}
