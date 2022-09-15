package com.example.appserver.controller;

import com.example.appserver.domain.Timer;
import com.example.appserver.domain.UserProfile;
import com.example.appserver.repository.UserProfileRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TimerController {

    private UserProfileRepository userProfileRepository;

    @PostMapping("/timer/on")
    public void timerOn(@PathVariable("id") String id,@RequestParam("isOn") boolean isOn) {
        Optional<UserProfile> user=userProfileRepository.findById(Long.parseLong(id));
    }

}
