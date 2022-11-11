package com.appserver.group;

import lombok.Data;

@Data
public class MemberGroupSearchCond {
    private String groupName;

    public MemberGroupSearchCond(){
    }

    public MemberGroupSearchCond(String groupName) { // 그룹 이름으로 검색
        this.groupName = groupName;
    }
}
