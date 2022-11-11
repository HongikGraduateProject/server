package com.appserver.group;

import com.appserver.community.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberGroupApiController {
    private final MemberGroupService memberGroupService;


    @PostMapping("/api/group")
    public Long save(@RequestBody MemberGroupDto requestDto){
        return memberGroupService.save(requestDto);
    }
    @PutMapping("/api/group/{id}")
    public Long update(@PathVariable Long id,@RequestBody MemberGroupUpdateDto requestDto){
        return memberGroupService.update(id,requestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
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
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    @GetMapping("/api/v1/post")
//    public List<Post> findAll(@RequestParam(required = false) String title,
//                              @RequestParam(required = false) String content,
//                              @RequestParam(required = false) String author){
//        List<Post> result=new ArrayList<>();
//        if(title != null) result= postService.findByTitle(title);
//        else if(content != null) result=postService.findByContent(content);
//        else if(author != null) result=postService.findByAuthor(author);
//        return result;
//    }
//    @DeleteMapping("/api/v1/post/{id}")
//    public void remove(@PathVariable Long id){
//        postService.delete(id);
//    }
}
