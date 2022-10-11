package com.example.appserver.api;

import com.example.appserver.domain.Task;
import com.example.appserver.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Slf4j
@RestController
public class TaskApiController {
    @Autowired
    TaskService taskService;

    //투두 조회
    //taskId를 유저별로 만들어지게 해야할듯 지금은 전체로 번호가 매겨진당..
    //근데 유저별로 하려면 유저별로 각 task repository를 가져야함(?!!!) 생각해보기....
    @GetMapping("/api/tasks/{memberId}/{taskId}")
    public Task findTask(@PathVariable Long memberId, @PathVariable Long taskId) {
        return taskService.findOne(taskId);
    }

    //유저가 쓴 전체 투두 조회
    @GetMapping("/api/tasks/{memberId}")
    public List<Task> findUserTask(@PathVariable Long memberId) {
        return taskService.findMemberTasks(memberId);
    }

    //투두 등록
    @PostMapping("/api/tasks/{memberId}")
    public void saveTask(@PathVariable Long memberId, @RequestBody Task taskData) {
        taskService.saveTask(memberId, taskData.getContents());
    }

    //투두 수정
    @PatchMapping("/api/tasks/{userId}/{taskId}")
    public void editTask(@PathVariable Long taskId, @RequestBody Task taskData) {
        taskService.editTask(taskId, taskData.getContents());
    }

    //투두 삭제 (예외처리 꼭 추가필요)
//    @DeleteMapping("/{userId}/{taskId}")
//    public void deleteTask(@PathVariable Long taskId) {
//        taskRepository.deleteById(taskId);
//    }

    @Data
    static class CreateTaskRequest {
        @NotEmpty
        private Long userId;
        @NotEmpty
        private String contents;
    }

    @Data
    static class CreateTaskResponse {
        private Long taskId;
        private String contents;

        public CreateTaskResponse(Long taskId, String contents) {
            this.taskId = taskId;
            this.contents = contents;
        }
    }

    @Data
    static class UpdateTaskRequest {
        private Long taskId;
        private String contents;
    }

    @Data
    @AllArgsConstructor
    static class UpdateTaskResponse {
        private Long taskId;
    }

}
