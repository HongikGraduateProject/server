package com.example.appserver.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Task {
    private Long id;
    private String contents;
    private Boolean isChecked;

    public Task(Long id, String contents) {
        this.id = id;
        this.contents = contents;
        this.isChecked = false;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", contents='" + contents + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
