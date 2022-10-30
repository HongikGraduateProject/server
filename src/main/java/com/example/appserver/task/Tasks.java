package com.example.appserver.task;

import com.example.appserver.domain.BaseTimeEntity;
import com.example.appserver.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
public class Tasks extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, columnDefinition = "TEXT", nullable = false)
    private String contents;

    private Boolean isChecked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member; //작성 회원

    private Date date;

    //==생성 메소드==//
    @Builder
    public Tasks(Member member, String contents, Date date) {
        this.member = member;
        this.contents = contents;
        this.isChecked = false;
        this.date = date;
    }

    public void check() {
        Boolean status = this.getIsChecked();
        //this.setIsChecked(!status);
    }

    public void update(String contents) {
        this.contents = contents;
    }
}
