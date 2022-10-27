package com.example.appserver.community.repository;

import com.example.appserver.community.Post;
import com.example.appserver.community.PostSearchCond;
import com.example.appserver.community.PostUpdateRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    void update(Long postId, PostUpdateRequestDto updateParam);
    Optional<Post> findById(Long id);
    List<Post> findAll(PostSearchCond cond);
    void delete(Long id);

}
