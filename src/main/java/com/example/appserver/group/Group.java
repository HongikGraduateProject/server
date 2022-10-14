package com.example.appserver.group;

import com.example.appserver.member.Member;

import java.util.ArrayList;
import java.util.List;


public class Group {
    Long id;
    String name;

    private List<Member> members = new ArrayList<Member>();

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
