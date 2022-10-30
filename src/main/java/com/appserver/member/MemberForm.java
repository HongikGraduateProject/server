package com.appserver.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "회원 이메일은 필수 입니다")
    private String email;
    private String username;
    private String password;
    private String passwordCheck;
}
