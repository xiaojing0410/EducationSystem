package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Honor {
    private Integer id;
    private Integer student_id;
    private String honor_name;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDateTime award_date;
    private Integer type;
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getHonor_name() {
        return honor_name;
    }

    public void setHonor_name(String honor_name) {
        this.honor_name = honor_name;
    }

    public LocalDateTime getAward_date() {
        return award_date;
    }

    public void setAward_date(LocalDateTime award_date) {
        this.award_date = award_date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

//    public Honor(Integer student_id, String honor_name, LocalDateTime award_date, Integer type) {
//        this.student_id = student_id;
//        this.honor_name = honor_name;
//        this.award_date = award_date;
//        this.type = type;
//    }
}
