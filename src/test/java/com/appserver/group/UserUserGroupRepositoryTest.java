package com.appserver.group;

import com.appserver.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@SpringBootTest
class UserUserGroupRepositoryTest {

    @Autowired
    UserGroupRepository userGroupRepository;

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

        userGroupRepository.save(group);
    }
}