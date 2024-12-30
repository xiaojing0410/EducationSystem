package com.example.demo.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class HonorDto {
    private String honor_name;
    private LocalDateTime award_date;
    private Integer type;

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
}
