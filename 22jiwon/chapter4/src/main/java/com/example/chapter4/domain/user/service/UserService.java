package com.example.chapter4.domain.user.service;

import com.example.chapter4.domain.user.dto.UserLoginRequest;
import com.example.chapter4.domain.user.dto.UserLoginResponse;
import com.example.chapter4.domain.user.dto.UserSignupRequest;
import com.example.chapter4.domain.user.dto.UserSignupResponse;
import com.example.chapter4.domain.user.dto.UserSummary;
import com.example.chapter4.domain.user.entity.User;
import com.example.chapter4.domain.user.entity.UserRole;
import com.example.chapter4.domain.user.repository.UserRepository;
import com.example.chapter4.global.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // 회원가입
    public UserSignupResponse signup(UserSignupRequest request) {

        // 중복 체크 (email을 username으로 쓰는 경우)
        if (userRepository.existsByUsername(request.getEmail())) {
            // 실전에서는 커스텀 예외 / 에러코드로 던지면 더 좋음
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // User 엔티티 생성
        User user = User.builder()
                // 여기서 username = email 로 사용하는 설계
                .username(request.getEmail())
                .password(encodedPassword)
                .nickname(request.getNickname())
                .phone(request.getPhone())
                .marketingAgree(request.isMarketingAgree())
                .role(UserRole.USER)
                .build();

        // 저장
        User saved = userRepository.save(user);

        // 응답 DTO 생성
        LocalDateTime createdAt =
                saved.getCreatedAt() != null ? saved.getCreatedAt() : LocalDateTime.now();

        return new UserSignupResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getNickname(),
                createdAt
        );
    }

    // 로그인
    public UserLoginResponse login(UserLoginRequest request) {

        // 아이디(이메일)로 유저 조회
        User user = userRepository.findByUsername(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 발급
        String accessToken = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user);

        // 응답 DTO 조립
        return new UserLoginResponse(
                accessToken,
                refreshToken,
                "Bearer",
                3600L,
                new UserSummary(
                        user.getId(),
                        user.getNickname()
                )
        );
    }
}