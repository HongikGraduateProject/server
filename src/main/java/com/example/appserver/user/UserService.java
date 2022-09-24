package com.example.appserver.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원 가입
     */
    public int join(User user) {
//        validateDuplicateMember(user); 오류나서 주석처리함
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
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findUser(Integer id){
        return userRepository.findById(id);
    }

    public void removeUser(Integer id){
        userRepository.deleteUser(id);
    }
}
