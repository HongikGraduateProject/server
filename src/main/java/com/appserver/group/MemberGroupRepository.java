package com.appserver.group;


import java.util.List;
import java.util.Optional;

public interface MemberGroupRepository {
    MemberGroup save(MemberGroup memberGroup);
    void update(Long groupId, MemberGroupUpdateDto updateParam);
    Optional<MemberGroup> findById(Long id);
    List<MemberGroup> findAll(String groupName);
    void delete(Long id);
}
