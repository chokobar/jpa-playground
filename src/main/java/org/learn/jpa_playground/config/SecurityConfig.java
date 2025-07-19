package org.learn.jpa_playground.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // OK
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/auth/login",
                                "/auth/login-process",
                                "/auth/logout",
                                "/save",
                                "/join", "/css/**",
                                "/member/memberAdd",
                                "/member/save",
                                "/member/members/**",
                                "/member/**",
                                "/members/**",
                                "/board/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }


    // 비밀번호 암호화 빈 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
