package com.appserver.login;

import com.appserver.member.Member;
import com.appserver.member.MemberRepository;
import lombok.RequiredArgsConstructor;
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
