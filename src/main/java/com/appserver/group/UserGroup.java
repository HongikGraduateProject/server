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
    @Column(name = "user_group_id")
    private Long id;

    @Column(name="group_name",length = 20)
    private String groupName;
    private Integer numOfMember;

    @OneToMany(mappedBy = "userGroup")
    private List<MakeGroup> makeGroupList=new ArrayList<>();

    public UserGroup(){

    }
    public UserGroup(String groupName, List<Member> members) {
        this.groupName = groupName;
    }

    public void setNumOfMember(List<Member> members) {
        this.numOfMember = members.size();
    }
}
