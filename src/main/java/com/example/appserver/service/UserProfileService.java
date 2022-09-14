package com.example.appserver.service;

import com.example.appserver.domain.UserProfile;
import com.example.appserver.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(UserProfile userProfile) {
        validateDuplicateMember(userProfile);
        userProfileRepository.save(userProfile);
        return userProfile.getId();
    }

    /**
     * 중복 회원 검증
     */
    private void validateDuplicateMember(UserProfile userProfile) {
        userProfileRepository.findByEmail(userProfile.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    private List<UserProfile> findMembers() {
        return userProfileRepository.findAll();
    }

}
