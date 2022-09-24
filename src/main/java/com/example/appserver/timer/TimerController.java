package com.example.appserver.timer;

import com.example.appserver.user.User;
import com.example.appserver.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TimerController {
    private com.example.appserver.user.UserRepository UserRepository;
    @GetMapping("/timer/on/{id}") // 유저의 타이머 on
    public void timerOn(@PathVariable("id") Integer id,@RequestParam("status") boolean status) {
        User user= UserRepository.findById(id);
        user.getTimer().timerOn();
        log.info("id={}",user.getTimer().getId());
    }
    @GetMapping("/timer/off/{id}") // 유저의 타이머 off
    public void timerOff(@PathVariable("id") Integer id,@RequestParam("status") boolean status) {
        User user= UserRepository.findById(id);
        int gold=user.getTimer().timerOff();
        user.setGold(gold);
    }
}
