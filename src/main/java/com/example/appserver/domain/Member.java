package com.example.appserver.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor

public class Member {
    private Long id;
    private String username;
    private String phoneNumber;
    private String emailAddress;
}
