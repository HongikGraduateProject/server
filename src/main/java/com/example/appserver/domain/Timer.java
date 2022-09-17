package com.example.appserver.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Timer {

    private final String id;
    private boolean status; // 타이머 작동 여부
    private Integer obtainedGold; // 획득 재화

    public Timer() { // 타이머 id는 uuid로 생성
        id = UUID.randomUUID().toString().substring(0,8);
    }

    public void timerOn() {
        status=true;
    }
    public int timerOff(){
        status=false;
        return obtainedGold;
    }
    public boolean getStatus() {
        return status;
    }
}
