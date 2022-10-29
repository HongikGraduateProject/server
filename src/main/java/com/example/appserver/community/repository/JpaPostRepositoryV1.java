package com.example.appserver.community.repository;

import com.example.appserver.community.Post;
import com.example.appserver.community.PostSearchCond;
import com.example.appserver.community.PostUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
    public List<Post> findByTitle(String title) {
        String jpql="select p from Post p";
        if (StringUtils.hasText(title)) {
            jpql += " where";
        }
        List<Object> param=new ArrayList<>();
        if(StringUtils.hasText(title)){
            jpql += " p.title like concat('%',:title,'%')";
            param.add(title);
        }
        log.info("jpql={}", jpql);
        TypedQuery<Post> query=em.createQuery(jpql,Post.class);
        if (StringUtils.hasText(title)) {
            query.setParameter("title", title);
        }
        return query.getResultList();
    }

    @Override
    public List<Post> findByContent(String content) {
        String jpql="select p from Post p";
        if (StringUtils.hasText(content)) {
            jpql += " where";
        }
        List<Object> param=new ArrayList<>();
        if(StringUtils.hasText(content)){
            jpql += " p.content like concat('%',:content,'%')";
            param.add(content);
        }
        log.info("jpql={}", jpql);
        TypedQuery<Post> query=em.createQuery(jpql,Post.class);
        if (StringUtils.hasText(content)) {
            query.setParameter("content", content);
        }
        return query.getResultList();
    }

    @Override
    public List<Post> findByAuthor(String author) {
        String jpql="select p from Post p";
        if (StringUtils.hasText(author)) {
            jpql += " where";
        }
        List<Object> param=new ArrayList<>();
        if(StringUtils.hasText(author)){
            jpql += " p.author like concat('%',:author,'%')";
            param.add(author);
        }
        log.info("jpql={}", jpql);
        TypedQuery<Post> query=em.createQuery(jpql,Post.class);
        if (StringUtils.hasText(author)) {
            query.setParameter("author", author);
        }
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        Post findPost=em.find(Post.class,id);
        em.remove(findPost);
    }
}
