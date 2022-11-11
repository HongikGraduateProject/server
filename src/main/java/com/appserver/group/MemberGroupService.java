package com.appserver.group;

import com.appserver.community.Post;
import com.appserver.community.PostResponseDto;
import com.appserver.community.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberGroupService {

    private final MemberGroupRepository memberGroupRepository;

    @Transactional
    public Long save(MemberGroupDto memberGroupDto){
        return memberGroupRepository.save(memberGroupDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id,MemberGroupUpdateDto requestDto){
        MemberGroup memberGroup=memberGroupRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 그룹이 없습니다. id="+id));

        return id;
    }

    public MemberGroupResponseDto findById(Long id){
        MemberGroup entity=memberGroupRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 그룹이 없습니다. id="+id));
        return new MemberGroupResponseDto(entity);
    }

    public List<MemberGroup> findAll(String groupName){
        return memberGroupRepository.findAll(groupName);
    }

    public void delete(Long id){
        memberGroupRepository.delete(id);
    }
}
