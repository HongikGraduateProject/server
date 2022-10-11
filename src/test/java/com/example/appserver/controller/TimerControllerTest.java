package com.example.appserver.controller;

import com.example.appserver.member.MemoryMemberRepository;
import com.example.appserver.member.Member;
import com.example.appserver.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class TimerControllerTest {
    MemberRepository userRepository =new MemoryMemberRepository();
//    @AfterEach
//    void afterEach(){
//        userRepository.clearStore();
//    }

    @Test
    void timerOn(){
        Member user1=new Member(1L,"alice","12345",
                "010-1111-1111",20,"student","합격","alice@gmail.com");
        Member user2=new Member(2L,"bob","abc",
                "010-2222-2222",25,"worker","이직","bob@gmail.com");

        log.info("timerId={}",user1.getTimer().getId());
        user1.getTimer().timerOn();
        user1.getTimer().setObtainedGold(500);
        log.info("timerStatus={} gold={}",user1.timerStatus(),user1.getTimer().getObtainedGold());
        int gold=user1.getTimer().timerOff();

        user1.setGold(gold);
        log.info("timerStatus={} gold={}",user1.timerStatus(),user1.getGold());

    }
}