package com.example.chapter4.domain.user.entity;

import com.example.chapter4.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_username", columnNames = "username")
        }
)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")               // ERD와 동일한 컬럼명
    private Long id;

    // 로그인 아이디(이메일)로 사용할 username
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    // 암호화된 비밀번호
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    // 사용자 닉네임
    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    // 전화번호
    @Column(name = "phone", length = 20)
    private String phone;

    // 마케팅 수신 동의 여부
    @Column(name = "marketing_agree", nullable = false)
    private boolean marketingAgree;

    // 권한 (USER / OWNER / ADMIN 등)
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private UserRole role;

    @Builder
    public User(String username,
                String password,
                String nickname,
                String phone,
                boolean marketingAgree,
                UserRole role) {

        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.marketingAgree = marketingAgree;
        this.role = role;
    }
}