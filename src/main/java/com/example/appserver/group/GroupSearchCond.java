package com.example.appserver.group;

import lombok.Data;

@Data
public class GroupSearchCond {
    private String groupName;

    public GroupSearchCond(){
    }

    public GroupSearchCond(String groupName) {
        this.groupName = groupName;
    }
}
