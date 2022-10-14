package com.example.appserver.member;

import com.example.appserver.domain.BaseTimeEntity;
import com.example.appserver.domain.Tasks;
import com.example.appserver.timer.Timer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // 일반 유저, 관리자 구분

    @OneToOne
    @JoinColumn(name="TIMER_ID")
    private Timer timer;
    private int gold;

//    @OneToMany(mappedBy = "Tasks")
//    private List <Tasks> tasks = new ArrayList<Tasks>();

    private String phoneNumber;
    private int age;
    private String goal;

    @Builder
    public Member(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public boolean timerStatus(){
        return timer.getStatus();
    }

    public void setGold(int gold) {
        this.gold += gold;
    }

    public Member update(String username) {
        this.username = username;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
