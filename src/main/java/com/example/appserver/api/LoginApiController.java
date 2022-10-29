package com.example.appserver.api;

import com.example.appserver.login.JwtTokenProvider;
import com.example.appserver.member.Member;
import com.example.appserver.oauth.GetSocialOAuthRes;
import com.example.appserver.oauth.OauthService;
import com.example.appserver.oauth.SocialType;
import com.example.appserver.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;
    private final OauthService oauthService;

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

    @GetMapping("/api/login/auth/{socialLoginType}")
    public void socialLoginRedirect(@PathVariable(name="socialLoginType") String SocialLoginPath) throws IOException {
        log.info("{}", SocialLoginPath);
        SocialType socialType = SocialType.valueOf(SocialLoginPath.toUpperCase());
        oauthService.request(socialType);
    }

    @GetMapping("/api/login/auth/{socialLoginType}/callback")
    public SuccessResult<LoginResponse> callback(@PathVariable(name="socialLoginType") String SocialLoginPath, @RequestParam String code) throws IOException {
        log.info("code = {}", code);
        SocialType socialType = SocialType.valueOf(SocialLoginPath.toUpperCase());
        GetSocialOAuthRes getSocialOAuthRes = oauthService.oAuthLogin(socialType, code);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail(getSocialOAuthRes.getEmail());
        loginResponse.setId(getSocialOAuthRes.getId());
        loginResponse.setUsername(getSocialOAuthRes.getUsername());
        log.info("email = {} id = {} name = {}", loginResponse.getEmail(), loginResponse.getId(), loginResponse.getUsername());
        return new SuccessResult<>(getSocialOAuthRes.getJwtToken(), loginResponse);
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
            this.message = "login success";
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

    @Data
    static class SocialResponse {
        private String email;
        private String username;
    }

    @GetMapping("/hihi")
    public String testMapping() {
        return "hello";
    }

}
