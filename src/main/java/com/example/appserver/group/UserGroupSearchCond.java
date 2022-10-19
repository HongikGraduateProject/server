package com.example.appserver.group;

import lombok.Data;

@Data
public class UserGroupSearchCond {
    private String groupName;

    public UserGroupSearchCond(){
    }

    public UserGroupSearchCond(String groupName) { // 그룹 이름으로 검색
        this.groupName = groupName;
    }
}
