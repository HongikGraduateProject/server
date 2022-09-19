package com.example.appserver.controller;

import com.example.appserver.domain.User;
import com.example.appserver.repository.UserRepository;
import com.example.appserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    private Map<String, User> userMap;

    @PostConstruct
    public void init(){ // 테스트용 데이터
        userMap=new HashMap<String, User>();
        userMap.put("1",new User(1,"abc","b12345","01012341234",
                17,"e","f","g"));
        userMap.put("2",new User(2,"cde","b25555","01011111111",
                20,"x","f","g"));
        userMap.put("3",new User(3,"fff","bdd999","01054320000",
                25,"v","f","g"));
    }
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") String id){
        return userMap.get(id);
    }

    @GetMapping("/user/all")
    public List<User> getUserList(){
        return new ArrayList<User>(userMap.values());
    }

    @PostMapping ("/user/{id}")
    public void postUser(@PathVariable("id") String id,
                                @RequestParam("username") String username,
                                @RequestParam("phoneNumber") String phoneNumber){
        User user =new User(Integer.parseInt(id),username,phoneNumber);
        userMap.put(id, user);
    }
    @PutMapping ("/user/{id}")
    public void putUser(@PathVariable("id") String id,
                               @RequestParam("username") String username,
                               @RequestParam("phoneNumber") String phoneNumber){
        User user =userMap.get(id);
        user.setUsername(username);
        user.setPhoneNumber(phoneNumber);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(){

    }

}
