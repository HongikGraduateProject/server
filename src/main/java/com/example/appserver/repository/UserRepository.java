package com.example.appserver.repository;

import com.example.appserver.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    User save(User user); // 회원 저장
    User findById(Integer id); // 회원 아이디로 찾기
//    User findByUsername(String username); // 회원 이름으로 찾기
    Optional<User> findByEmail(String emailAddress); // NULL을 반환할 때, Optional로 감싸서 반환함. java8의 기능.
    List<User> findAll(); // 모든 회원 리스트 반환

    void clearStore();
}