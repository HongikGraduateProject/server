package com.example.appserver.member;

import com.example.appserver.domain.Task;
import com.example.appserver.timer.Timer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    private String username;
    private String email;
    private String password;

    private String nickName;

    private String phoneNumber;
    private int age;
    private String job;
    private String goal;

    @OneToOne
    @JoinColumn(name="TIMER_ID")
    private Timer timer;
    private int gold;

    @OneToMany
    @JoinColumn(name="MEMBER_ID")
    private List <Task> tasks = new ArrayList<Task>();

    @Enumerated(EnumType.STRING)
    private Role role; // 일반 유저, 관리자 구분
    private LocalDateTime createDate; // 가입 날짜

    public Member() {
    }

    public Member(Long id, String username, String password, String nickName, String email, String phoneNumber,
                  int age, String job, String goal, Timer timer,
                  int gold, Role role, LocalDateTime createDate) {
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

    public Member(Long id, String username, String password, String phoneNumber, int age, String job, String goal, String email) {
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

    public Member(Long id, String username, String phoneNumber){
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
