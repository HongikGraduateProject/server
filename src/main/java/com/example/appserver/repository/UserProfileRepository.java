package com.example.appserver.repository;

import com.example.appserver.domain.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository {
    UserProfile save(UserProfile userProfile); // 회원 저장
    Optional<UserProfile> findById(Long id); // 회원 찾기
    Optional<UserProfile> findByEmail(String emailAddress); // NULL을 반환할 때, Optional로 감싸서 반환함. java8의 기능.
    List<UserProfile> findAll(); // 모든 회원 리스트 반환
}
