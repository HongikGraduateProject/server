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
    public void init(){
        userMap=new HashMap<String,UserProfile>();
        userMap.put("1",new UserProfile(1L,"a","b","c",
                17,"e","f","g"));
        userMap.put("2",new UserProfile(2L,"a1","b2","c3",
                20,"e","f","g"));
        userMap.put("3",new UserProfile(3L,"abc","bdd","c",
                25,"e","f","g"));
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
}
