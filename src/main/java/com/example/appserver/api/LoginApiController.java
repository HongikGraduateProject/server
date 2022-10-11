package com.example.appserver.api;

import com.example.appserver.login.JwtTokenProvider;
import com.example.appserver.member.Member;
import com.example.appserver.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/login")
    public SuccessResult<LoginResponse> loginApi(@RequestBody LoginRequest request) {
        Member loginMember = loginService.login(request.getEmail(), request.getPassword());
        log.info("login ? {}", loginMember);

        if (loginMember == null) {
            //throw new LoginFailureException();
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail(loginMember.getEmail());
        loginResponse.setId(loginMember.getId());
        loginResponse.setUsername(loginMember.getUsername());

        return new SuccessResult<>(jwtTokenProvider.createToken(request.getEmail()), loginResponse);
    }

    @Data
    @AllArgsConstructor
    static class SuccessResult<T> {
        private String result;
        private Long expires_in;
        private String token;
        private String message;
        private T user_info;

        public SuccessResult(String token, T user_info) {
            this.result = result = "success";
            this.expires_in = 30 * 60 * 1000L;
            this.token = token;
            this.message = message = "로그인 성공";
            this.user_info = user_info;
        }
    }

    @Data
    static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    static class LoginResponse {
        private Long id;
        private String username;
        private String email;
    }

    @GetMapping("/hihi")
    public String testMapping() {
        return "hello";
    }

}
