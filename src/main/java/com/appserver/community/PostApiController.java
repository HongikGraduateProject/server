package com.appserver.community;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/post")
    public Long save(@RequestBody PostSaveRequestDto requestDto){
        return postService.save(requestDto);
    }

    @PutMapping("/api/post/{id}")
    public Long update(@PathVariable Long id,@RequestBody PostUpdateRequestDto requestDto){
        return postService.update(id,requestDto);
    }

    @GetMapping("/api/post/{id}")
    public PostResponseDto findById(@PathVariable Long id){
        return postService.findById(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/api/post")
    public List<Post> findAll(@RequestParam(required = false) String title,
                                                              @RequestParam(required = false) String content,
                                                              @RequestParam(required = false) String author){
        List<Post> result=new ArrayList<>();
        if(title != null) result= postService.findByTitle(title);
        else if(content != null) result=postService.findByContent(content);
        else if(author != null) result=postService.findByAuthor(author);
        return result;
    }
    @DeleteMapping("/api/post/{id}")
    public void remove(@PathVariable Long id){
        postService.delete(id);
    }
}
