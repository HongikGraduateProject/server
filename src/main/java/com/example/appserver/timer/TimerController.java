package com.example.appserver.timer;

import com.example.appserver.user.User;
import com.example.appserver.user.UserRepository;
import com.example.appserver.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TimerController {
    private final UserService userService;

    @GetMapping("/timer/on/{id}") // 유저의 타이머 on
    public void timerOn(@PathVariable("id") Integer id,@RequestParam("status") boolean status) {
        if(status==false) {
            User user=userService.findUser(id);
            user.getTimer().timerOn();
            user.getTimer().setStatus(true);
            log.info("id={},status={}",user.getTimer().getId(),user.getTimer().getStatus());
        }
    }
    @GetMapping("/timer/off/{id}") // 유저의 타이머 off
    public void timerOff(@PathVariable("id") Integer id,@RequestParam("status") boolean status) {
        if(status==true) {
            User user=userService.findUser(id);
            int gold = user.getTimer().timerOff();
            user.setGold(gold);
            user.getTimer().setStatus(false);
            log.info("id={},status={}",user.getTimer().getId(),user.getTimer().getStatus());
        }
    }
    @GetMapping("/timer/{id}") // 유저의 타이머 정보 조회
    public String getTimerInfo(@PathVariable("id") Integer id){
        User user=userService.findUser(id);
        return user.getTimer().getId();
    }
}
