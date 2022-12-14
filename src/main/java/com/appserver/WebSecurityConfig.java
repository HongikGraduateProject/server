package com.appserver;

import com.appserver.login.JwtAuthenticationFilter;
import com.appserver.login.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/main").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/members/new").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/login/**").permitAll()
                .antMatchers("/api/members").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
