package com.example.appserver.controller;

import com.example.appserver.domain.LoginForm;
import com.example.appserver.service.LoginService;
import com.example.appserver.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    /**
     * 틀만 구현함!!!
     * 쿠키 등등 여러가지 로직들 추가해야함!!!!!!!!
     */
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "fail";
        }
        User loginUser = loginService.login(loginForm.getEmail(), loginForm.getPassword());
        log.info("login User = {}, ",loginUser);
        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "fail";
        }
        return "success";
    }
}
