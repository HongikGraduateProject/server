package com.example.appserver.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Task {
    private Long taskId;
    private Long userId;
    private String contents;
    private Boolean isChecked;

    public Task(Long userId, String contents, Boolean isChecked) {
        this.userId = userId;
        this.contents = contents;
        this.isChecked = isChecked;
    }
}
