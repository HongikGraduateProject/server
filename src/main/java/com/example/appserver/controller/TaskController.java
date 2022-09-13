package com.example.appserver.controller;

import com.example.appserver.domain.Task;
import com.example.appserver.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/task/{userId}")
    public List<Task> userTask(@PathVariable Long userId) {
        taskRepository.findAll(userId);
        List<Task> userTasks = taskRepository.findAll(userId);
        return userTasks;
    }
}
