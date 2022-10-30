package com.appserver.oauth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NaverOAuthToken {
    private String access_token;
    private String refresh_token;
    private String token_type;
    private int expires_in;
//    private String error;
//    private String error_description;
}
