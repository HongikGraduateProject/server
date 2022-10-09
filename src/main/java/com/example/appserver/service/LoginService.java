package com.example.appserver.service;

import com.example.appserver.user.Member;
import com.example.appserver.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * return null이면 로그인 실패
     */
    public Member login(String email, String password) {
        return memberRepository.findByEmail(email).stream()
                .filter(m -> m.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
