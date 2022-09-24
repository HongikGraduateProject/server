package com.example.appserver.user;

import com.example.appserver.timer.Timer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class User {
    private int id;

    private String username;
    private String password;
    private String nickName;
    private String email;
    private String phoneNumber;

    private int age;
    private String job;
    private String goal;

    private Timer timer;
    private int gold;

    private String role; // 일반 유저, 관리자 구분
    private LocalDateTime createDate; // 가입 날짜

    public User() {
    }

    public User(int id, String username, String password, String phoneNumber, int age, String job, String goal, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.job = job;
        this.goal = goal;
        this.email = email;
        timer=new Timer();
        gold=0;
    }

    public User(int id, String username, String phoneNumber){
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public boolean timerStatus(){
        return timer.getStatus();
    }

}
