package com.example.appserver.controller;

import com.example.appserver.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private MemberRepository memberRepository;

}
