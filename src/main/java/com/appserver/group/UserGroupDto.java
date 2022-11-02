package com.appserver.group;

import com.appserver.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class UserGroupDto {
    private String groupName;
    private Integer numOfMember;
    private String groupTask;

    private List<Member> members = new ArrayList<>();

    public UserGroupDto(String groupName, Integer numOfMember, String groupTask, List<Member> members) {
        this.groupName = groupName;
        this.numOfMember = numOfMember;
        this.groupTask = groupTask;
        this.members = members;
    }
}
