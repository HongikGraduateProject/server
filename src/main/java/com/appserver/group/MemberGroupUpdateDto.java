package com.appserver.group;

import com.appserver.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberGroupUpdateDto {
    private String groupName;
    private String groupTask;

    @Builder
    public MemberGroupUpdateDto(String groupName, String groupTask) {
        this.groupName = groupName;
        this.groupTask = groupTask;
    }
}
