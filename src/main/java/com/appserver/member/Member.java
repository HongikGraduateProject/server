package com.appserver.member;

import com.appserver.domain.BaseTimeEntity;
import com.appserver.group.UserGroup;
import com.appserver.makegroup.MakeGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // 일반 유저, 관리자 구분

    @OneToMany(mappedBy = "member")
    private List<MakeGroup> makeGroupList=new ArrayList<>();

    private Long studyTime=0L;
    private Integer level = 0;
    private Integer gold = 0;



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

    public Member setTime(Long time) {
        this.studyTime += time;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
