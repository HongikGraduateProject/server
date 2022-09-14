package com.example.appserver.controller;

import com.example.appserver.domain.Task;
import com.example.appserver.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TaskController {

    private TaskRepository taskRepository;

    @PostMapping("/tasktest")
    public void taskSave(@RequestBody Long userId, @RequestBody String contents) {
        log.info("username={}, age={}", userId, contents);
//        taskRepository.save(task.getId(), task);
    }
}
