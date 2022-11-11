package com.appserver.group;


import com.appserver.makegroup.MakingGroup;
import com.appserver.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class MemberGroup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_group_id")
    private Long id;

    @Column(name="group_name",length = 20)
    private String groupName;
    private String groupTask;

    @OneToMany(mappedBy = "memberGroup")
    private List<MakingGroup> makingGroupList =new ArrayList<>();

    public MemberGroup(){

    }
    @Builder
    public MemberGroup(String groupName, String groupTask) {
        this.groupName = groupName;
        this.groupTask=groupTask;
    }

}
