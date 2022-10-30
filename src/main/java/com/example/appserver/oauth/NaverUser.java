package com.example.appserver.oauth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NaverUser {
    public String id;
    public String nickname;
    public String email;
}
