package com.example.chapter4.domain.member.service.query;

import com.example.chapter4.domain.member.converter.MemberConverter;
import com.example.chapter4.domain.member.dto.req.MemberReqDto;
import com.example.chapter4.domain.member.dto.res.MemberResDto;
import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.member.exception.MemberException;
import com.example.chapter4.domain.member.exception.code.MemberErrorCode;
import com.example.chapter4.domain.member.repository.MemberRepository;
import com.example.chapter4.global.security.CustomUserDetails;
import com.example.chapter4.global.security.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public MemberResDto.LoginDTO login(MemberReqDto.@Valid LoginDTO dto) {
        // Member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDTO(member, accessToken);
    }
}
