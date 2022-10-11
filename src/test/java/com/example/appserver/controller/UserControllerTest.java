package com.example.appserver.controller;

import com.example.appserver.member.MemoryMemberRepository;
import com.example.appserver.member.Member;
import com.example.appserver.member.MemberRepository;
import com.example.appserver.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class UserControllerTest {

    MemberRepository userRepository=new MemoryMemberRepository();

    MemberService userService=new MemberService(userRepository);

    @Test
    void test(){
        Member user1=new Member(1L,"alice","12345",
                "010-1111-1111",20,"student","합격","alice@gmail.com");
        Member user2=new Member(2L,"bob","abc",
                "010-2222-2222",25,"worker","이직","bob@gmail.com");
        userRepository.save(user1);

        log.info("userid={}",user1.getId());
        Member findMember =userRepository.findById(user1.getId());
        assertThat(findMember).isEqualTo(user1);
    }

}