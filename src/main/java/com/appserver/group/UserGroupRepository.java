package com.appserver.group;


import java.util.List;
import java.util.Optional;

public interface UserGroupRepository {
    UserGroup save(UserGroup userGroup);
    void update(Long groupId, UserGroupUpdateDto updateParam);
    Optional<UserGroup> findById(Long id);
    List<UserGroup> findAll(UserGroupSearchCond cond);
    void delete(Long id);
}
