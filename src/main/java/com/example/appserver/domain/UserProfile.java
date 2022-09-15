package com.example.appserver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class UserProfile {
    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    private int age;
    private String job;
    private String goal;
    private String email;
    private Timer timer;

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public UserProfile(Long id, String username, String password, String phoneNumber, int age, String job, String goal, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.job = job;
        this.goal = goal;
        this.email = email;
    }

    public UserProfile(Long id, String username, String phoneNumber){
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }


}
