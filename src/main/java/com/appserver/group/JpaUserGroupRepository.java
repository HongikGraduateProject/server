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
public class JpaUserGroupRepository implements UserGroupRepository {

   private final EntityManager em;

    public JpaUserGroupRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public UserGroup save(UserGroup userGroup) {
        em.persist(userGroup);
        return userGroup;
    }

    @Override
    public void update(Long groupId, UserGroupUpdateDto updateParam) {
        UserGroup findGroup=em.find(UserGroup.class,groupId);
        findGroup.setGroupName(updateParam.getGroupName());
    }

    @Override
    public Optional<UserGroup> findById(Long id) {
        UserGroup group=em.find(UserGroup.class,id);
        return Optional.ofNullable(group);
    }

    @Override
    public List<UserGroup> findAll(UserGroupSearchCond cond) {
        String jpql="select g from UserGroup g";

        String groupName=cond.getGroupName();

        if (StringUtils.hasText(groupName)) {
            jpql += " where";
        }

        List<Object> param = new ArrayList<>();
        if (StringUtils.hasText(groupName)) {
            jpql += " g.groupName like concat('%',:groupName,'%')";
            param.add(groupName);
        }
        log.info("jpql={}", jpql);

        TypedQuery<UserGroup> query=em.createQuery(jpql, UserGroup.class);
        if (StringUtils.hasText(groupName)) {
            query.setParameter("groupName", groupName);
        }

        return query.getResultList();
    }

    @Override
    public void delete(Long id) {

    }
}
