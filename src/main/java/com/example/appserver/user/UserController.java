package com.example.appserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private Map<String, User> userMap;

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

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") String id){
        return userService.findUser(Integer.parseInt(id));
    }

    @GetMapping("/user/all")
    public List<User> getUserList(){
        return userService.findUsers();
    }

//    http://localhost:8080/user/1?username=kim&phoneNumber=01011111111
//    http://localhost:8080/user/2?username=lee&phoneNumber=01022222222
//    http://localhost:8080/user/3?username=park&phoneNumber=01033333333
    @PostMapping ("/user/{id}")
    public void postUser(@PathVariable("id") String id,
                                @RequestParam("username") String username,
                                @RequestParam("phoneNumber") String phoneNumber){
        User user =new User(Integer.parseInt(id),username,phoneNumber);
        userService.join(user);
    }
    @PatchMapping ("/user/{id}") // 회원정보 수정
    public void putUser(@PathVariable("id") String id,
                               @RequestParam("username") String username,
                               @RequestParam("phoneNumber") String phoneNumber){
        User user =userService.findUser(Integer.parseInt(id));
        user.setUsername(username);
        user.setPhoneNumber(phoneNumber);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") String id){
        userService.removeUser(Integer.parseInt(id));
    }

//    참고용
//    @ResponseBody
//    @PostMapping("/request-body-json-v5")
//    public Hellodata requestBodyJsonV5(@RequestBody Hellodata data){
//        log.info("username={}, age={}", data.getUsername(), data.getAge());
//        return data;
//    }
}
