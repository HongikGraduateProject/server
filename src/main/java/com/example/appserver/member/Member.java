package com.example.appserver.member;

import com.example.appserver.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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


    private Long studyTime=0L;
    private Integer level = 0;
    private Integer gold = 0;

//    @OneToMany(mappedBy = "Tasks")
//    private List <Tasks> tasks = new ArrayList<Tasks>();

    private String goal;

    @Builder
    public Member(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
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
