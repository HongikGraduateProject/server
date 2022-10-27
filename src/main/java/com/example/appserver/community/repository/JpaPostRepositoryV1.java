package com.example.appserver.community.repository;

import com.example.appserver.community.Post;
import com.example.appserver.community.PostSearchCond;
import com.example.appserver.community.PostUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
public class JpaPostRepositoryV1 implements PostRepository{
    private final EntityManager em;

    public JpaPostRepositoryV1(EntityManager em){
        this.em=em;
    }

    @Override
    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public void update(Long postId, PostUpdateRequestDto updateParam) {
        Post findPost=em.find(Post.class,postId);
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent(updateParam.getContent());
    }

    @Override
    public Optional<Post> findById(Long id) {
        Post post=em.find(Post.class,id);
        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> findAll(PostSearchCond cond) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Post findPost=em.find(Post.class,id);
        em.remove(findPost);
    }
}
