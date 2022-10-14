package com.example.appserver.timer;

import com.example.appserver.member.Member;
import com.example.appserver.member.MemberService;
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
    private final MemberService memberService;

    @GetMapping("/timer/on/{id}") // 유저의 타이머 on
    public void timerOn(@PathVariable("id") Long id,@RequestParam("status") boolean status) {
        if(status==false) {
            Member user=memberService.findById(id);
            user.getTimer().timerOn();
            user.getTimer().setStatus(true);
            log.info("id={},status={}",user.getTimer().getId(),user.getTimer().getStatus());
        }
    }
    @GetMapping("/timer/off/{id}") // 유저의 타이머 off
    public void timerOff(@PathVariable("id") Long id,@RequestParam("status") boolean status) {
        if(status==true) {
            Member user=memberService.findById(id);
            int gold = user.getTimer().timerOff();
            user.setGold(gold);
            user.getTimer().setStatus(false);
            log.info("id={},status={}",user.getTimer().getId(),user.getTimer().getStatus());
        }
    }
    @GetMapping("/timer/{id}") // 유저의 타이머 정보 조회
    public String getTimerInfo(@PathVariable("id") Long id){
        Member user=memberService.findById(id);
        return user.getTimer().toString();
    }
}
