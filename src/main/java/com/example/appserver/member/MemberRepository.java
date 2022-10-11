package com.example.appserver.member;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository {
    void save(Member member); // 회원 저장
    Member findById(Long id); // 회원 아이디로 찾기
    List<Member> findByEmail(String email); // NULL을 반환할 때, Optional로 감싸서 반환함. java8의 기능.
    List<Member> findAll(); // 모든 회원 리스트 반환
//    void deleteMember(Long id);
//    void clearStore();
}
