package com.example.appserver.controller;

import com.example.appserver.user.MemoryUserRepository;
import com.example.appserver.user.User;
import com.example.appserver.user.UserRepository;
import com.example.appserver.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class UserControllerTest {

    UserRepository userRepository=new MemoryUserRepository();

    UserService userService=new UserService(userRepository);


    @AfterEach
    void afterEach(){
        userRepository.clearStore();
    }

    @Test
    void test(){
        User user1=new User(1L,"alice","12345",
                "010-1111-1111",20,"student","합격","alice@gmail.com");
        User user2=new User(2L,"bob","abc",
                "010-2222-2222",25,"worker","이직","bob@gmail.com");
        userRepository.save(user1);

        log.info("userid={}",user1.getId());
        User findUser=userRepository.findById(user1.getId());
        assertThat(findUser).isEqualTo(user1);
    }

}