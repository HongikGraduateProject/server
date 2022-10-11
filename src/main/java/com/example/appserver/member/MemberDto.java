package com.example.appserver.member;

import com.example.appserver.timer.Timer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {

    private String username;
    private String email;
    private String password;

    private String nickName;

    private String phoneNumber;
    private int age;
    private String job;
    private String goal;
    private Timer timer;
    private int gold;

    private String role; // 일반 유저, 관리자 구분
    private LocalDateTime createDate; // 가입 날짜

    public MemberDto() {
    }

    public MemberDto(String username, String password, String nickName, String email, String phoneNumber,
                     int age, String job, String goal, Timer timer,
                     int gold, String role, LocalDateTime createDate) {
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.job = job;
        this.goal = goal;
        timer=new Timer();
        gold=0;
        this.role = role;
        this.createDate = createDate;
    }

}
