package com.appserver.makegroup;

import com.appserver.group.MemberGroup;
import com.appserver.member.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class MakingGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "member_group_id")
    private MemberGroup memberGroup;

    @Builder
    public MakingGroup(Member member, MemberGroup memberGroup) {
        this.member = member;
        this.memberGroup = memberGroup;
    }
}