package com.example.appserver.service;

import com.example.appserver.domain.Member;
import com.example.appserver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복 회원 검증
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByEmail(member.getEmailAddress())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    private List<Member> findMembers() {
        return memberRepository.findAll();
    }

}
