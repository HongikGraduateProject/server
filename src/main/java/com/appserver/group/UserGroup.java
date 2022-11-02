package com.appserver.group;


import com.appserver.member.Member;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UserGroup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="group_name",length = 20)
    private String groupName;
    private Integer numOfMember;

    @OneToMany
    @JoinColumn(name="group_id")
    private List<Member> members = new ArrayList<>();

    public UserGroup(){

    }
    public UserGroup(String groupName, List<Member> members) {
        this.groupName = groupName;
        this.members=members;
    }

    public void setNumOfMember(List<Member> members) {
        this.numOfMember = members.size();
    }
}
