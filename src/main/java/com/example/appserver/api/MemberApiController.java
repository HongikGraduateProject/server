package com.example.appserver.api;

import com.example.appserver.domain.LoginForm;
import com.example.appserver.user.Member;
import com.example.appserver.user.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberApiController {
    /**
     * 실무에서는 절대로 entity를 외부에 노출하거나 파라미터로 받으면 안된다
     * api는 요청, 응답 모두 DTO를 사용해야 한다
     */

    private final MemberService memberService;

//    @DeleteMapping("/members/{id}")
//    public void deleteMember(@PathVariable("id") Long id) {
//        memberService.removeMember(id);
//    }

    /**
     * 회원 조회
     */
    @GetMapping("/api/members")
    public Result memberAll() {
        List<Member> findUsers = memberService.findMembers();
        List<MemberDto> collect = findUsers.stream()
                .map(u -> new MemberDto(u.getUsername(), u.getEmail()))
                .collect(Collectors.toList());
        return new Result<>(collect);
    }

    @GetMapping("/api/user/{id}")
    public MemberInfoResponse getUser(@PathVariable("id") Long id) {
        Member user = memberService.findOne(id);
        return new MemberInfoResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        //일단 고객의 이름과 이메일만 반환하도록 함
        private String name;
        private String email;
    }

    @Data
    static class MemberInfoResponse {
        //비번 일단 뺌
        private Long id;
        private String username;
        private String email;

        public MemberInfoResponse(Long id, String username, String email) {
            this.id = id;
            this.username = username;
            this.email = email;
        }
    }

    /**
     * 회원 등록
     */

    @PostMapping("/api/members")
    public CreateMemberResponse joinMember(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setUsername(request.getUsername());
        member.setEmail(request.getEmail());
        member.setPassword(request.getPassword());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id, member.getUsername(),member.getEmail());
    }

    /**
     * 회원 수정
     */
    @PutMapping("/api/user/{id}")
    public UpdateMemberResponse updateUser(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getUsername(), request.getPassword());
        Member findUser = memberService.findOne(id);
        return new UpdateMemberResponse(id, findUser.getUsername(), findUser.getEmail());
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String email;
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;
        private String username;
        private String email;

        public CreateMemberResponse(Long id, String username, String email) {
            this.id = id;
            this.username = username;
            this.email = email;
        }
    }

    @Data
    static class UpdateMemberRequest {
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String username;
        private String email;
    }

    //    @PostConstruct
//    public void init(){ // 테스트용 데이터
//        userMap=new HashMap<String, User>();
//        userMap.put("1",new User(1,"abc","b12345","01012341234",
//                17,"e","f","g"));
//        userMap.put("2",new User(2,"cde","b25555","01011111111",
//                20,"x","f","g"));
//        userMap.put("3",new User(3,"fff","bdd999","01054320000",
//                25,"v","f","g"));
//    }

//    @PostMapping("/api/login")
//    public String login(@RequestBody @Valid LoginForm form) {
//        log.info("user email = {}", form.getEmail());
//        User member = userRepository.findByUserEmail(user.get("email"))
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
//
//        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
//    }
}
