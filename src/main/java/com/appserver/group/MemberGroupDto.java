package com.appserver.group;

import com.appserver.community.Post;
import com.appserver.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class MemberGroupDto {
    private String groupName;
    private String groupTask;

    public MemberGroupDto(String groupName, String groupTask) {
        this.groupName = groupName;
        this.groupTask = groupTask;
    }

    public MemberGroup toEntity(){
        return MemberGroup.builder().groupName(groupName).groupTask(groupTask).build();
    }
}
