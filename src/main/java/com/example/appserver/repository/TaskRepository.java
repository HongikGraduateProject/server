package com.example.appserver.repository;

import com.example.appserver.domain.Task;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class TaskRepository {

    @PersistenceContext
    EntityManager em;

    /**
     * 저장
     */
    public void save(Task task) {
        em.persist(task);
    }

    /**
     * 수정
     */
    public void edit(Long taskId, String updateContents) {
        Task findTask = findById(taskId);
        findTask.setContents(updateContents);
    }

    /**
     * 삭제
     */
    public void removeTask(Task task) {
        em.remove(task);
    }

    /**
     * 회원이 쓴 투두리스트 전체 조회
     */
    public List<Task> findMemberTaskAll(Long memberId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        Root<Task> t = cq.from(Task.class);

        Predicate idEqual = cb.equal(t.get("member").get("id"), memberId);
        javax.persistence.criteria.Order idDesc = cb.asc(t.get("id"));

        cq.select(t).where(idEqual).orderBy(idDesc);

        List<Task> resultList = em.createQuery(cq).getResultList();
        return resultList;
    }

    public Task findById(Long taskId) {
        return em.find(Task.class, taskId);
    }

//    public void deleteById(Long taskId) {
//        //예외처리 나중에 할예쩡
//        store.remove(taskId);
//    }
}
