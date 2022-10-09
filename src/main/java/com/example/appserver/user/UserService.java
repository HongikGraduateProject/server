package com.example.appserver.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(User user) {
        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 중복 회원 검증
     */
    private void validateDuplicateMember(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long id){
        return userRepository.findById(id);
    }

    public void removeUser(Long id){
        userRepository.deleteUser(id);
    }

    public void update(Long id, String username, String password) {
        User user = userRepository.findById(id);
        user.setUsername(username);
        user.setPassword(password);
    }
}
