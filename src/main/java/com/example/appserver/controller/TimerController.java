package com.example.appserver.controller;

import com.example.appserver.domain.Timer;
import com.example.appserver.domain.UserProfile;
import com.example.appserver.repository.MemoryUserProfileRepository;
import com.example.appserver.repository.UserProfileRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TimerController {
    private UserProfileRepository UserProfileRepository;
    @PutMapping("/timer/on/{id}") // 유저의 타이머 on
    public void timerOn(@PathVariable("id") Long id,@RequestParam("status") boolean status) {
        UserProfile user=UserProfileRepository.findById(id);
        user.getTimer().timerOn();

    }
    @PutMapping("/timer/off/{id}") // 유저의 타이머 off
    public void timerOff(@PathVariable("id") Long id,@RequestParam("status") boolean status) {
        UserProfile user=UserProfileRepository.findById(id);
        int gold=user.getTimer().timerOff();
        user.setGold(gold);
    }
}
