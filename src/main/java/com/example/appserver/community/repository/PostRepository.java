package com.example.appserver.community.repository;

import com.example.appserver.community.Post;
import com.example.appserver.community.PostSearchCond;
import com.example.appserver.community.PostUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    void update(Long postId, PostUpdateRequestDto updateParam);
    Optional<Post> findById(Long id);
    List<Post> findByTitle(String title);
    List<Post> findByContent(String content);
    List<Post> findByAuthor(String author);
    void delete(Long id);
}
