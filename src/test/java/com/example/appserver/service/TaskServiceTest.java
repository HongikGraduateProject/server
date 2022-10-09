package com.example.appserver.service;

import com.example.appserver.domain.Task;
import com.example.appserver.repository.TaskRepository;
import com.example.appserver.user.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TaskServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    TaskService taskService;
    @Autowired
    TaskRepository taskRepository;

    @Test
    public void 투두작성() {
        Member member = createMember();
        System.out.println(member.getId());

        Long taskId1 = taskService.saveTask(member.getId(), "member1의 1번째 투두");
        Long taskId2 = taskService.saveTask(member.getId(), "member1의 2번째 투두");
        Long taskId3 = taskService.saveTask(member.getId(), "member1의 3번째 투두");

        Task task = taskRepository.findById(taskId1);
        List<Task> memberTasks = taskService.findMemberTasks(member.getId());

        for (Task tasks:memberTasks){
            System.out.println(tasks.getId() + "컨텐츠:" + tasks.getContents());
        }

        Assertions.assertThat(task.getMember().getId()).isEqualTo(member.getId());
    }

    private Member createMember() {
        Member member1 = new Member();
        member1.setEmail("member1@gmail.com");
        em.persist(member1);
        return member1;
    }
}