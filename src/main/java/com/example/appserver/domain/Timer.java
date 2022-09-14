package com.example.appserver.domain;

import lombok.Data;

@Data
public class Timer {

    private boolean isOn; // 타이머 작동 여부
    private int obtainedGold; // 획득 재화

    public Timer() {
    }

    public Timer(boolean isOn, int obtainedGold) {
        this.isOn = isOn;
        this.obtainedGold = obtainedGold;
    }
}
