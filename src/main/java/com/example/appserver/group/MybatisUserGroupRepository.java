package com.example.appserver.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MybatisUserGroupRepository implements UserGroupRepository{

    private final UserGroupMapper userGroupMapper;

    @Override
    public UserGroup save(UserGroup userGroup) {
        userGroupMapper.save(userGroup);
        return userGroup;
    }

    @Override
    public void update(Long groupId, UserGroupUpdateDto updateParam) {
        userGroupMapper.update(groupId,updateParam);
    }

    @Override
    public Optional<UserGroup> findById(Long id) {
        return userGroupMapper.findById(id);
    }

    @Override
    public List<UserGroup> findAll(UserGroupSearchCond cond) {
        return userGroupMapper.findAll(cond);
    }

    @Override
    public void delete(Long id) {
        userGroupMapper.delete(id);
    }
}
