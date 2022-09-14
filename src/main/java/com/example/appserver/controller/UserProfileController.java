package com.example.appserver.controller;

import com.example.appserver.domain.UserProfile;
import com.example.appserver.repository.UserProfileRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserProfileController {

    private UserProfileRepository userProfileRepository;

    private Map<String, UserProfile> userMap;

    @PostConstruct
    public void init(){ // 테스트용 데이터
        userMap=new HashMap<String,UserProfile>();
        userMap.put("1",new UserProfile(1L,"abc","b12345","01012341234",
                17,"e","f","g"));
        userMap.put("2",new UserProfile(2L,"cde","b25555","01011111111",
                20,"x","f","g"));
        userMap.put("3",new UserProfile(3L,"fff","bddggg","01054320000",
                25,"v","f","g"));
    }
    @GetMapping("/user/{id}")
    public UserProfile getUserProfile(@PathVariable("id") String id){
        return userMap.get(id);
    }

    @GetMapping("/user/all")
    public List<UserProfile> getUserProfileList(){
        return new ArrayList<UserProfile>(userMap.values());
    }

    @PostMapping ("/user/{id}")
    public void postUserProfile(@PathVariable("id") String id,
                                @RequestParam("username") String username,
                                @RequestParam("phoneNumber") String phoneNumber){
        UserProfile userProfile=new UserProfile(Long.parseLong(id),username,phoneNumber);
        userMap.put(id,userProfile);
    }
    @PutMapping ("/user/{id}")
    public void putUserProfile(@PathVariable("id") String id,
                               @RequestParam("username") String username,
                               @RequestParam("phoneNumber") String phoneNumber){
        UserProfile userProfile=userMap.get(id);
        userProfile.setUsername(username);
        userProfile.setPhoneNumber(phoneNumber);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserProfile(){

    }
}
