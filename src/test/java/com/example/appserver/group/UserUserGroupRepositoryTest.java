package com.example.appserver.group;

import com.example.appserver.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserUserGroupRepositoryTest {

    @Test
    void test(){
        Member member1=new Member();
        Member member2=new Member();
        Member member3=new Member();
        List<Member> members = new ArrayList<Member>();

        members.add(member1);
        members.add(member2);
        members.add(member3);

        log.info("{}",members.size());

        UserGroup group=new UserGroup();
        group.setGroupName("group1");
        group.setMembers(members);

    }
}