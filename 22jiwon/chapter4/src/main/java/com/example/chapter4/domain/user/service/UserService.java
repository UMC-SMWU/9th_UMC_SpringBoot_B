package com.example.chapter4.domain.user.service;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.chapter4.domain.user.entity.User;
import com.example.chapter4.domain.user.entity.UserRole;
import com.example.chapter4.domain.user.repository.UserRepository;
import com.example.chapter4.domain.user.dto.UserSignupRequest;
import com.example.chapter4.domain.user.dto.UserSignupResponse;
import com.example.chapter4.domain.user.dto.UserLoginRequest;
import com.example.chapter4.domain.user.dto.UserLoginResponse;
import com.example.chapter4.domain.user.dto.UserSummary;
import com.example.chapter4.global.security.JwtProvider;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserSignupResponse signup(UserSignupRequest request) {
        // 1. 이메일(또는 username) 중복 체크
        if (userRepository.existsByUsername(request.getEmail())) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }
        // 2. 유저 엔티티 생성 & 저장
        User user = User.builder()
                .username(request.getEmail())
                .role(UserRole.USER)
                .build();
        userRepository.save(user);

        // 3. 응답 DTO 반환
        return new UserSignupResponse(
                user.getId(),
                user.getUsername(),
                user.getUsername(), // nickname 필드가 User 엔티티에 별도로 있다면 수정
                user.getCreatedAt() // BaseEntity에 createdAt이 있다면
        );
    }

    public UserLoginResponse login(UserLoginRequest request) {
        User user = userRepository.findByUsername(request.getEmail())
                .orElseThrow(() -> new RuntimeException("이메일 또는 비밀번호가 틀렸습니다."));

        // 비밀번호 체크 (passwordEncoder 사용 예시)
        // if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) { ... }

        // JWT 토큰 생성
        String accessToken = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user);

        return new UserLoginResponse(
                accessToken,
                refreshToken,
                "Bearer",
                3600L,
                new UserSummary(user.getId(), user.getUsername() /* 또는 nickname */)
        );
    }
}

