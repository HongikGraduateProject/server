package com.appserver.group;

import com.appserver.member.Member;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserGroupUpdateDto {
    private String groupName;
    private Integer numOfMember;
    private List<Member> members = new ArrayList<Member>();

    public UserGroupUpdateDto(){

    }
    public UserGroupUpdateDto(String groupName, List<Member> members) {
        this.groupName = groupName;
        this.members=members;
    }

    public void setNumOfMember(List<Member> members) {
        this.numOfMember = members.size();
    }
}
