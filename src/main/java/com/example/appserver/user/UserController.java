package com.example.appserver.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    /**
     * 실무에서는 절대로 entity를 외부에 노출하거나 파라미터로 받으면 안된다
     * api는 요청, 응답 모두 DTO를 사용해야 한다
     */

    private final UserService userService;

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
    }

    /**
     * 회원 조회
     */
    @GetMapping("/api/user")
    public Result memberAll() {
        List<User> findUsers = userService.findUsers();
        List<UserDto> collect = findUsers.stream()
                .map(u -> new UserDto(u.getUsername(), u.getEmail()))
                .collect(Collectors.toList());
        return new Result<>(collect);
    }

    @GetMapping("/api/user/{id}")
    public UserInfoResponse getUser(@PathVariable("id") Long id) {
        User user = userService.findUser(id);
        return new UserInfoResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class UserDto {
        //일단 고객의 이름과 이메일만 반환하도록 함
        private String name;
        private String email;
    }

    @Data
    static class UserInfoResponse {
        //비번 일단 뺌
        private Long id;
        private String username;
        private String email;

        public UserInfoResponse(Long id, String username, String email) {
            this.id = id;
            this.username = username;
            this.email = email;
        }
    }

    /**
     * 회원 등록
     */

    @PostMapping("/api/user")
    public CreateUserResponse joinUser(@RequestBody @Valid CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        Long id = userService.join(user);
        return new CreateUserResponse(id, user.getUsername(),user.getEmail());
    }

    /**
     * 회원 수정
     */
    @PutMapping("/api/user/{id}")
    public UpdateUserResponse updateUser(@PathVariable("id") Long id, @RequestBody @Valid UpdateUserRequest request) {
        userService.update(id, request.getUsername(), request.getPassword());
        User findUser = userService.findUser(id);
        return new UpdateUserResponse(id, findUser.getUsername(), findUser.getEmail());
    }

    @Data
    static class CreateUserRequest {
        @NotEmpty
        private String email;
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
    }

    @Data
    static class CreateUserResponse {
        private Long id;
        private String username;
        private String email;

        public CreateUserResponse(Long id, String username, String email) {
            this.id = id;
            this.username = username;
            this.email = email;
        }
    }

    @Data
    static class UpdateUserRequest {
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class UpdateUserResponse {
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

}
