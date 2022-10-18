package com.example.appserver.group;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface GroupMapper {
    void save(Group group);
    void update(@Param("id") Long id, @Param("updateParam") Group updateParam);
    Optional<Group> findById(Long id);
    List<Group> findAll(GroupSearchCond itemSearch);
    void delete(@Param("id") Long id);
}
