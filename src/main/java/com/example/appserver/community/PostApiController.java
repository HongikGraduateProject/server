package com.example.appserver.community;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @GetMapping("/api/v1/get")
    public void getTest(){
        log.info("success get");
    }

    @PostMapping("/api/v1/post")
    public Long save(@RequestBody PostSaveRequestDto requestDto){
        log.info("save success, {}, {}",requestDto.getTitle(),requestDto.getContent());
        return postService.save(requestDto);
    }

    @PutMapping("/api/v1/post/{id}")
    public Long update(@PathVariable Long id,@RequestBody PostUpdateRequestDto requestDto){
        return postService.update(id,requestDto);
    }
    @GetMapping("/api/v1/post/{id}")
    public PostResponseDto findById(@PathVariable Long id){
        return postService.findById(id);
    }
}
