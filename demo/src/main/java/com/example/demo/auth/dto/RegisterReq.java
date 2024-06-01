package com.example.demo.auth.dto;


import lombok.Data;

import java.util.Date;

@Data
public class RegisterReq {
    private String name;
    private String email;
    private String password;
    private Boolean admin=false ;
}