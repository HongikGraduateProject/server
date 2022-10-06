package com.example.appserver.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userservice;
    MemoryUserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository = new MemoryUserRepository();
        userservice = new UserService(userRepository);
    }
    @AfterEach
    public void afterEach() {
        userRepository.clearStore();
    }

    //given, when, then 문법
    @Test
    void 회원가입() {
        //given
        User user = new User();
        user.setUsername("user1");
        user.setEmail("user1@gmail.com");
        user.setPassword("123456");

        //when
        userservice.join(user);

        //then
        User findUser = userservice.findUser(user.getId());
        assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
    }

    @Test
    public void 중복_회원_예외(){
        //give
        User user1 = new User();
        user1.setUsername("user1");
        user1.setEmail("user1@gmail.com");
        user1.setPassword("123456");

        User user2 = new User();
        user2.setUsername("user1");
        user2.setEmail("user1@gmail.com");
        user2.setPassword("123456");

        //when
        userservice.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userservice.join(user2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일입니다.");
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}