package com.example.chapter4.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; // ◀◀ import 추가
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain; // ◀◀ import 추가

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 1. CSRF, Form Login, HTTP Basic 비활성화 (JWT 사용을 위함)
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        // 2. 세션 정책을 STATELESS로 설정 (세션 사용 안 함)
        http
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 3. URL별 접근 권한 설정
        http
                .authorizeHttpRequests(auth -> auth
                        // 기존 허용 경로
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        // [추가] 리뷰, 미션 등 테스트용 API 허용
                        .requestMatchers("/api/v1/reviews/**", "/api/v1/reviews", "/missions/**", "/api/v1/stores", "/api/v1/stores/**", "/regions", "/regions/**").permitAll()
                        // 그 외 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                );

        // (추후 JWT 필터를 여기에 추가)

        return http.build();
    }
}