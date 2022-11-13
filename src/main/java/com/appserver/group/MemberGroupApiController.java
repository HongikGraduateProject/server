package com.appserver.group;

import com.appserver.community.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberGroupApiController {
    private final MemberGroupService memberGroupService;

    @PostMapping("/api/group")
    public Long save(@RequestBody MemberGroupDto requestDto){
        return memberGroupService.save(requestDto);
    }

    @PostMapping("/api/group/{id}")
    public Long update(@PathVariable Long id,@RequestBody MemberGroupUpdateDto requestDto){
        return memberGroupService.update(id,requestDto);
    }

    @GetMapping("/api/group/{id}")
    public MemberGroupResponseDto findById(@PathVariable Long id){
        return memberGroupService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/api/group")
    public List<MemberGroup> findAll(@RequestParam(required = false) String groupName){
        List<MemberGroup> result=new ArrayList<>();
        result=memberGroupService.findAll(groupName);
        return result;
    }

    @DeleteMapping("/api/group/{id}")
    public void remove(@PathVariable Long id){
        memberGroupService.delete(id);
    }
}
