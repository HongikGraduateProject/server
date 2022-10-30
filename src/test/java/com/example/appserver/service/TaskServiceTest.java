package com.example.appserver.service;

import com.example.appserver.task.TaskService;
import com.example.appserver.task.Tasks;
import com.example.appserver.task.TaskRepository;
import com.example.appserver.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

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

        Long taskId1 = taskService.save(member.getId(), "member1의 1번째 투두");
        Long taskId2 = taskService.save(member.getId(), "member1의 2번째 투두");
        Long taskId3 = taskService.save(member.getId(), "member1의 3번째 투두");

        Tasks task = taskRepository.findById(taskId1).get();
        List<Tasks> memberTasks = taskService.findMemberTasks(member.getId());

        for (Tasks tasks:memberTasks){
            System.out.println(tasks.getId() + "컨텐츠:" + tasks.getContents());
        }

        Assertions.assertThat(task.getMember().getId()).isEqualTo(member.getId());
    }

    private Member createMember() {
        Member member1 = Member.builder()
                .username("member1")
                .email("member1@gmail.com")
                .build();
        em.persist(member1);
        return member1;
    }
}