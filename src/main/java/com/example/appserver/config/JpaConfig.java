package com.example.appserver.config;

import com.example.appserver.community.PostService;
import com.example.appserver.community.repository.JpaPostRepositoryV1;
import com.example.appserver.community.repository.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class JpaConfig {
    private final EntityManager em;
    public JpaConfig(EntityManager em) {
        this.em = em;
    }

}
