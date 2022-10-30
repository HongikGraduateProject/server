package com.appserver.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<com.appserver.task.Tasks, Long> {
    List <com.appserver.task.Tasks> findByMember_Id(Long memberId);

    @Query("SELECT t FROM Tasks t ORDER BY t.id DESC")
    List <com.appserver.task.Tasks> findAllTasks();

}
