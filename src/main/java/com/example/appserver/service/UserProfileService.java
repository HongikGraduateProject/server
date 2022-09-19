package com.example.appserver.service;

import com.example.appserver.domain.User;
import com.example.appserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {

    private final UserRepository userRepository;

    public UserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원 가입
     */
    public int join(User user) {
        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 중복 회원 검증
     */
    private void validateDuplicateMember(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    private List<User> findMembers() {
        return userRepository.findAll();
    }

}
