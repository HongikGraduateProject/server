package com.example.appserver.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Timer {

    private boolean isOn; // 타이머 작동 여부
    private Integer obtainedGold; // 획득 재화

    public Timer() {
    }

    public Timer(boolean isOn, Integer obtainedGold) {
        this.isOn = isOn;
        this.obtainedGold = obtainedGold;
    }
}
