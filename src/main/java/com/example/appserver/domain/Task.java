package com.example.appserver.domain;

import com.example.appserver.user.Member;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="TASKS")
public class Task {

    @Id @GeneratedValue
    @Column(name="TASK_ID")
    private Long id;
    private String contents;
    private Boolean isChecked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member; //작성 회원

    //==생성 메소드==//
    public static Task createTask(Member member, String contents) {
        Task task = new Task();
        task.setContents(contents);
        task.setIsChecked(false);
        task.setMember(member);
        return task;
    }

    public void check() {
        Boolean status = this.getIsChecked();
        this.setIsChecked(!status);
    }
}
