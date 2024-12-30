package com.example.demo.dto.student;

import com.example.demo.entity.Student;

public class StudentDto {
    //  "oldpassword": "123",
    //    "password": "234",
    //    "email": "r.jmnhmsgezm@qq.com",
    private String password;
    private String email;

    private String oldpassword;
    private String phone;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }
}
