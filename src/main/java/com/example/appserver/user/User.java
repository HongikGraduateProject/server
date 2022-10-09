package com.example.appserver.user;

import com.example.appserver.timer.Timer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class User {

    private Long id;

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

    public User() {
    }

    public User(Long id, String username, String password, String nickName, String email, String phoneNumber,
                int age, String job, String goal, Timer timer,
                int gold, String role, LocalDateTime createDate) {
        this.id = id;
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

    public User(Long id, String username, String password, String phoneNumber, int age, String job, String goal, String email) {
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

    public User(Long id, String username, String phoneNumber){
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        timer=new Timer();
        gold=0;
    }

    public boolean timerStatus(){
        return timer.getStatus();
    }

    public void setGold(int gold) {
        this.gold += gold;
    }
}
