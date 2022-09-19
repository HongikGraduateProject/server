package com.example.appserver.controller;

import com.example.appserver.domain.User;
import com.example.appserver.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimerController {
    private UserRepository UserRepository;
    @PutMapping("/timer/on/{id}") // 유저의 타이머 on
    public void timerOn(@PathVariable("id") Integer id,@RequestParam("status") boolean status) {
        User user= UserRepository.findById(id);
        user.getTimer().timerOn();

    }
    @PutMapping("/timer/off/{id}") // 유저의 타이머 off
    public void timerOff(@PathVariable("id") Integer id,@RequestParam("status") boolean status) {
        User user= UserRepository.findById(id);
        int gold=user.getTimer().timerOff();
        user.setGold(gold);
    }
}
