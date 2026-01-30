package com.dpt.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf()
                .and()
            .authorizeRequests()
                .antMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")              // GET handled by controller
                .loginProcessingUrl("/login")     // POST handled by security
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error")
                .and()
            .logout()
                .logoutSuccessUrl("/login?logout");

        return http.build();
    }
}
    