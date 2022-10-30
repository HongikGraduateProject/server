package com.appserver.community;


import com.appserver.community.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

//    @AfterEach
//    public void tearDown() throws Exception{
//        postRepository.deleteAll();
//    }

//    @Test
//    public void Post_등록된다() throws Exception{
//        //given
//        String title="title";
//        String content="content";
//
//       PostSaveRequestDto requestDto=PostSaveRequestDto.builder()
//               .title(title)
//               .content(content)
//               .author("author")
//               .build();
//
//       String url="http://localhost:"+port+"/api/v1/post";
//
//       //when
//        ResponseEntity<Long> responseEntity=restTemplate.postForEntity(url,requestDto, Long.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//        List<Post> all=postRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//    }
//
//    @Test
//    public void update() throws Exception{
//        //given
//       Post savedPost=postRepository.save(Post.builder()
//                .title("title")
//                .content("content")
//                .author("author")
//                .build());
//
//       Long updateId=savedPost.getId();
//       String expectedTitie="title2";
//       String expectedContent="content2";
//
//       PostUpdateRequestDto requestDto=PostUpdateRequestDto.builder()
//               .title(expectedTitie)
//               .content(expectedContent)
//               .build();
//
//       String url="http://localhost:"+port+"/api/v1/post"+updateId;
//
//        HttpEntity<PostUpdateRequestDto> requestEntity=new HttpEntity<>(requestDto);
//
//        //when
//        ResponseEntity<Long> responseEntity=restTemplate.exchange(url, HttpMethod.PUT,requestEntity,Long.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
}