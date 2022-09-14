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
    public void save(Long memberId, String contents) {
        Task task = new Task(memberId, contents);
        store.put(sequence++, task);
    }

    /**
     * 각 회원이 쓴 투두리스트 출력
     */
    public List<Task> findAll(Long memberId) {
        Stream<Task> taskStream = store.values().stream().filter(task -> task.getId().equals(memberId));
        List<Task> list = taskStream.collect(Collectors.toList());
        return list;
    }
}
