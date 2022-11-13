package com.appserver.group;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
public class JpaMemberGroupRepository implements MemberGroupRepository {

   private final EntityManager em;

    public JpaMemberGroupRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public MemberGroup save(MemberGroup memberGroup) {
        em.persist(memberGroup);
        return memberGroup;
    }

    @Override
    public void update(Long groupId, MemberGroupUpdateDto updateParam) {
        MemberGroup findGroup=em.find(MemberGroup.class,groupId);
        findGroup.setGroupName(updateParam.getGroupName());
        findGroup.setGroupTask(updateParam.getGroupTask());
    }

    @Override
    public Optional<MemberGroup> findById(Long id) {
        MemberGroup group=em.find(MemberGroup.class,id);
        return Optional.ofNullable(group);
    }

    @Override
    public List<MemberGroup> findAll(String groupName) {
        String jpql="select g from MemberGroup g";


        if (StringUtils.hasText(groupName)) {
            jpql += " where";
        }

        List<Object> param = new ArrayList<>();
        if (StringUtils.hasText(groupName)) {
            jpql += " g.groupName like concat('%',:groupName,'%')";
            param.add(groupName);
        }
        log.info("jpql={}", jpql);

        TypedQuery<MemberGroup> query=em.createQuery(jpql, MemberGroup.class);
        if (StringUtils.hasText(groupName)) {
            query.setParameter("groupName", groupName);
        }

        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        MemberGroup findPost=em.find(MemberGroup.class,id);
        em.remove(findPost);
    }
}
