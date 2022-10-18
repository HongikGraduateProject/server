package com.example.appserver.group;

import com.example.appserver.member.Member;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupUpdateDto {
    private String name;
    private List<Member> members = new ArrayList<Member>();

    public GroupUpdateDto(){

    }
    public GroupUpdateDto(Long id, String name) {
        this.name = name;
        this.members=members;
    }
}
