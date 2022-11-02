package com.appserver.makegroup;

import com.appserver.group.UserGroup;
import com.appserver.member.Member;
<<<<<<< HEAD
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
=======
import lombok.Getter;
>>>>>>> origin/master
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
<<<<<<< HEAD
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
=======
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

>>>>>>> origin/master
}
