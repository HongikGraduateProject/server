package com.example.appserver.controller;

import com.example.appserver.domain.Timer;
import com.example.appserver.domain.UserProfile;
import com.example.appserver.repository.MemoryUserProfileRepository;
import com.example.appserver.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TimerControllerTest {
    UserProfileRepository userProfileRepository=new MemoryUserProfileRepository();
    @AfterEach
    void afterEach(){
        userProfileRepository.clearStore();
    }

    @Test
    void timerOn(){
        UserProfile user1=new UserProfile(1L,"alice","12345",
                "010-1111-1111",20,"student","합격","alice@gmail.com");
        UserProfile user2=new UserProfile(2L,"bob","abc",
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