package com.example.appserver.service;

import com.example.appserver.user.User;
import com.example.appserver.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    @Autowired
    private final UserRepository userRepository;

    /**
     * return null이면 로그인 실패
     */
    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
