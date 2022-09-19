package com.example.appserver.controller;

import com.example.appserver.domain.User;
import com.example.appserver.repository.UserRepository;
import com.example.appserver.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class adminController {

    private UserService userService;
    private UserRepository userRepository;

    @GetMapping("/user/admin")
    public String adminPage(){
        return "test2";
    }
}
