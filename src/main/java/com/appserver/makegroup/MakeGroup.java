package com.appserver.makegroup;

import com.appserver.group.UserGroup;
import com.appserver.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class MakeGroup {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;

    @Builder
    public MakeGroup(Member member, UserGroup userGroup) {
        this.member = member;
        this.userGroup = userGroup;
    }
}
