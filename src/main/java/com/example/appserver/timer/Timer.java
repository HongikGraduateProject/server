package com.example.appserver.timer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Random;
import java.util.UUID;

@Getter @Setter
@Entity
public class Timer {

    @Id
    @Column(name="TIMER_ID")
    private final String id; // 타이머 고유 id
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
        Random obtainedGold=new Random();
        return obtainedGold.nextInt(1000); // 일단은 서버 자체의 랜덤 클래스 사용
    }
    public boolean getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return id+", "+status;
    }
}
