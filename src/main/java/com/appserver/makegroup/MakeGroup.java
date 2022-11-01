package com.appserver.makegroup;

import com.appserver.group.UserGroup;
import com.appserver.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class MakeGroup {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "makeGroup_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="group_id")
    private UserGroup userGroup;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

}
