package com.example.appserver.service;

import com.example.appserver.domain.UserProfile;
import com.example.appserver.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(UserProfile userProfile) {
        validateDuplicateMember(userProfile);
        memberRepository.save(userProfile);
        return userProfile.getId();
    }

    /**
     * 중복 회원 검증
     */
    private void validateDuplicateMember(UserProfile userProfile) {
        memberRepository.findByEmail(userProfile.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    private List<UserProfile> findMembers() {
        return memberRepository.findAll();
    }

}
