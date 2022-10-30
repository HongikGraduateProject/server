package com.appserver.task;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class TaskForm {
    @NotEmpty(message = "내용은 필수 입니다")
    private String contents;
}
