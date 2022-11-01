package com.appserver.group;


import com.appserver.makegroup.MakeGroup;
import com.appserver.member.Member;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class UserGroup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(name="group_name",length = 20)
    private String groupName;
    private Integer numOfMember;
    private String groupTask;

    @OneToMany(mappedBy = "userGroup")
    private List<MakeGroup> makeGroupList = new ArrayList<>();

    public UserGroup(){

    }
    public UserGroup(String groupNames) {
        this.groupName = groupName;
        numOfMember=0;
    }

    public void setNumOfMember(List<Member> members) {
        this.numOfMember = members.size();
    }
}
