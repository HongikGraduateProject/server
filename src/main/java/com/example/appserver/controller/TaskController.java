package com.example.appserver.controller;

import com.example.appserver.domain.Task;
import com.example.appserver.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    //투두 조회
    //taskId를 유저별로 만들어지게 해야할듯 지금은 전체로 번호가 매겨진당..
    //근데 유저별로 하려면 유저별로 각 task repository를 가져야함(?!!!) 생각해보기....
    @GetMapping("/{userId}/{taskId}")
    public Task findTask(@PathVariable Long userId, @PathVariable Long taskId) {
        return taskRepository.findById(taskId);
    }

    //유저가 쓴 전체 투두 조회
    @GetMapping("/{userId}")
    public List<Task> findUserTask(@PathVariable Long userId) {
        return taskRepository.findUserTaskAll(userId);
    }

    //투두 등록
    @PostMapping("/{userId}")
    public void saveTask(@PathVariable Long userId, @RequestBody Task taskData) {
        taskRepository.save(userId, taskData.getContents(), taskData.getIsChecked());
    }

    //투두 수정
    @PatchMapping("/{userId}/{taskId}")
    public void editTask(@PathVariable Long taskId, @RequestBody Task taskData) {
        taskRepository.edit(taskId, taskData.getContents(), taskData.getIsChecked());
    }

    //투두 삭제 (예외처리 꼭 추가필요)
    @DeleteMapping("/{userId}/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
