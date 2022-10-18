package com.example.appserver.group;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {
    void save(Group group);
    void update(Long groupId, Group updateParam);
    Optional<Group> findById(Long id);
    List<Group> findAll(GroupSearchCond itemSearch);
    void delete(@Param("id") Long id);
}
