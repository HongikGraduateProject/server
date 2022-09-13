package com.example.appserver.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private Long id;
    private String username;
    private String phoneNumber;
    private String emailAddress;
}
