package com.example.appserver.group;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserGroupMapper {
    void save(UserGroup userGroup);
    void update(@Param("id") Long id, @Param("updateParam") UserGroupUpdateDto updateParam);
    Optional<UserGroup> findById(Long id);
    List<UserGroup> findAll(UserGroupSearchCond userGroupSearch);
    void delete(@Param("id") Long id);
}
