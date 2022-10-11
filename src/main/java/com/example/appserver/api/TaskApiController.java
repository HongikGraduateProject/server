package com.example.appserver.api;

import com.example.appserver.domain.Task;
import com.example.appserver.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class TaskApiController {
    @Autowired
    TaskService taskService;

    //투두 조회
    @GetMapping("/api/tasks/{memberId}/{taskId}")
    public TaskDto findTask(@PathVariable Long memberId, @PathVariable Long taskId) {
        Task task = taskService.findOne(taskId);
        return new TaskDto(task.getId(), task.getContents());
    }

    //유저가 쓴 전체 투두 조회
    @GetMapping("/api/tasks/{memberId}")
    public Result findUserTask(@PathVariable Long memberId) {
        List<Task> memberTasks = taskService.findMemberTasks(memberId);
        List<TaskDto> collect = memberTasks.stream()
                .map(t -> new TaskDto((t.getId()), t.getContents()))
                .collect(Collectors.toList());
        return new Result<>(collect);
    }

    /**
     * 투두 등록
     */

    @PostMapping("/api/tasks/{memberId}")
    public CreateTaskResponse saveTask(@PathVariable Long memberId, @RequestBody @Valid CreateTaskRequest request) {
        Long taskId = taskService.saveTask(memberId, request.getContents());
        return new CreateTaskResponse(taskId, taskService.findOne(taskId).getContents());
    }

    /**
     * 투두 수정
     */
    @PutMapping("/api/tasks/{memberId}/{taskId}")
    public UpdateTaskResponse updateUser(@PathVariable Long taskId, @RequestBody @Valid UpdateTaskRequest request) {
        Long id = taskService.editTask(taskId, request.getContents());
        return new UpdateTaskResponse(id, taskService.findOne(taskId).getContents());
    }

    //투두 삭제 (예외처리 꼭 추가필요)
    @DeleteMapping("/api/tasks/{userId}/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.removeTask(taskId);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class TaskDto {
        private Long taskId;
        private String contents;
    }

    @Data
    static class CreateTaskRequest {
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
        @NotEmpty
        private String contents;
    }

    @Data
    @AllArgsConstructor
    static class UpdateTaskResponse {
        private Long taskId;
        private String contents;
    }

}
