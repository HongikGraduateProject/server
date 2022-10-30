package com.example.appserver.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List <Tasks> findByMember_Id(Long memberId);

    @Query("SELECT t FROM Tasks t ORDER BY t.id DESC")
    List <Tasks> findAllTasks();

//    public List<Task> findMemberTaskAll(Long memberId) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Task> cq = cb.createQuery(Task.class);
//        Root<Task> t = cq.from(Task.class);
//
//        Predicate idEqual = cb.equal(t.get("member").get("id"), memberId);
//        javax.persistence.criteria.Order idDesc = cb.asc(t.get("id"));
//
//        cq.select(t).where(idEqual).orderBy(idDesc);
//
//        List<Task> resultList = em.createQuery(cq).getResultList();
//        return resultList;
//    }
}
