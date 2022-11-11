package com.appserver.group;


public class MemberGroupResponseDto {
    private Long id;
    private String groupName;
    private String groupTask;

    public MemberGroupResponseDto(MemberGroup entity) {
        this.id = entity.getId();
        this.groupName = entity.getGroupName();
        this.groupTask = entity.getGroupTask();
    }
}
