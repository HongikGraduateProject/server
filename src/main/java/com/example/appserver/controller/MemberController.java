package com.example.appserver.controller;

import com.example.appserver.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private MemberRepository memberRepository;

//    @PostMapping("/")
//    public Member requestBodyJsonV5(@RequestBody HelloData data) {
//        log.info("username={}, age={}", data.getUsername(), data.getAge());
//        return data;
//    }

}
