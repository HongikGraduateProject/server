package com.example.appserver.service;

import com.example.appserver.domain.Task;
import com.example.appserver.repository.TaskRepository;
import com.example.appserver.member.Member;
import com.example.appserver.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TaskRepository taskRepository;

    /**
     * 투두 생성과 저장
     */
    public Long saveTask(Long memberId, String contents) {
        Member member = memberRepository.findById(memberId);
        Task task = Task.createTask(member, contents);
        taskRepository.save(task);
        return task.getId();
    }

    /**
     * 투두 수정
     */
    public Long editTask(Long taskId, String contents) {
        Task task = taskRepository.findById(taskId);
        task.setContents(contents);
        return task.getId();
    }

    /**
     * 투두 삭제
     */
    public void removeTask(Long taskId) {
        Task task = taskRepository.findById(taskId);
        taskRepository.removeTask(task);
    }

    /**
     * 전체 회원 조회
     */
    public List<Task> findMemberTasks(Long memberId) {
        return taskRepository.findMemberTaskAll(memberId);
    }

    /**
     * 투두 아이디로 조회
     */
    public Task findOne(Long taskId){
        return taskRepository.findById(taskId);
    }
}
