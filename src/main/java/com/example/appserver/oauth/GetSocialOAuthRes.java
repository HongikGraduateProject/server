package com.example.appserver.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetSocialOAuthRes {
    private String jwtToken;
    private Long id;
    private String email;
    private String username;
    private String accessToken;
    private String tokenType;
}
