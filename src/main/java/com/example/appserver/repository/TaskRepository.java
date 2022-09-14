package com.example.appserver.repository;

import com.example.appserver.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class TaskRepository {

    private static final Map<Long, Task> store = new HashMap<>();

    /**
     * 저장
     */
    public void save(Long memberId, String contents) {
        Task task = new Task(memberId, contents);
        store.put(memberId, task);
    }

    /**
     * 각 회원이 쓴 투두리스트 출력
     */
    public Optional<Task> findAll(Long memberId) {
        return store.values().stream()
                .filter(task -> task.getId().equals(memberId))
                .findAny();
    }
}
