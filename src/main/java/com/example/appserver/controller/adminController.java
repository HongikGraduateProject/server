package com.example.appserver.controller;

import com.example.appserver.user.User;
import com.example.appserver.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class adminController {

    private final UserService userService;

//    @GetMapping("user/admin")
//    public String adminPage(){
//        return "user/adminPage";
//    }

    @GetMapping("/users")
    public String list(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "user/userList";
    }
}
