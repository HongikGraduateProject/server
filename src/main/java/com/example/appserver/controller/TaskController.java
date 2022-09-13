package com.example.appserver.controller;

import com.example.appserver.domain.Task;
import com.example.appserver.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/task/save")
    public String taskSave(@RequestBody Task task) {
        log.info("username={}, age={}", task.getId(), task.getContents());
        taskRepository.save(task.getId(), task.getContents());
        return "저장완료";
    }

}
