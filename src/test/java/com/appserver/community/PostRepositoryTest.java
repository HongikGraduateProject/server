package com.appserver.community;

import com.appserver.community.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

//    @AfterEach
//    public void cleanup(){
//        postRepository.deleteAll();
//    }

    @Test
    void save() {
        //given
        Post post=new Post("title","content","author");
        //when
        Post savedPost=postRepository.save(post);
        //then
        Post findPost=postRepository.findById(post.getId()).get();
        assertThat(findPost).isEqualTo(savedPost);

    }
    @Test
    void updatePost() {
        //given
        Post post=new Post("title","content","author1");
        Post savedPost=postRepository.save(post);
        Long postId = savedPost.getId();

        //when
        PostUpdateRequestDto updateParam = new PostUpdateRequestDto("title2", "content2");
        postRepository.update(postId,updateParam);

        //then
        Post findPost=postRepository.findById(post.getId()).get();
        assertThat(findPost.getTitle()).isEqualTo(updateParam.getTitle());
        assertThat(findPost.getContent()).isEqualTo(updateParam.getContent());
    }
//    @Test
//    public void 게시글저장_불러오기(){
//        String title="테스트 게시글";
//        String content="테스트 본문";
//
//        postRepository.save(Post.builder().title(title).content(content).author("shkum").build());
//
//        List<Post> postList=postRepository.findAll();
//
//        Post post=postList.get(0);
//        assertThat(post.getTitle()).isEqualTo("테스트 게시글");
//        assertThat(post.getContent()).isEqualTo("테스트 본문");
//
//    }
}