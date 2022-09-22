package com.example.appserver.repository;

import com.example.appserver.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class TaskRepository {

    private static final Map<Long, Task> store = new HashMap<>();
    private static long sequence = 0L;

    /**
     * 저장
     */
    public void save(Long memberId, String contents, Boolean isChecked) {
        Task task = new Task(memberId, contents, isChecked);
        task.setTaskId(sequence++);
        store.put(task.getTaskId(), task);
    }

    /**
     * 수정
     */
    public void edit(Long taskId, String updateContents, Boolean isChecked) {
        Task findTask = findById(taskId);
        findTask.setContents(updateContents);
        findTask.setIsChecked(isChecked);
    }

    /**
     * 회원이 쓴 투두리스트 전체 조회
     */
    public List<Task> findUserTaskAll(Long memberId) {
        Stream<Task> taskStream = store.values().stream().filter(task -> task.getUserId().equals(memberId));
        return taskStream.collect(Collectors.toList());
    }

    public Task findById(Long taskId) {
        return store.get(taskId);
    }

    public void deleteById(Long taskId) {
        //예외처리 나중에 할예쩡
        store.remove(taskId);
    }
}
