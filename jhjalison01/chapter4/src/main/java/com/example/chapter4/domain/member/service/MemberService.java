package com.example.chapter4.domain.member.service;

import com.example.chapter4.domain.member.dto.MyPageInfoDto;
import com.example.chapter4.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //마이페이지 정보 조회
    public MyPageInfoDto getMyPageInfo(Long memberId) {
        MyPageInfoDto memberInfo = memberRepository.findMyPageInfoById(memberId);

        return new MyPageInfoDto(
                memberInfo.getName(),
                memberInfo.getEmail(),
                memberInfo.getPhoneNum(),
                memberInfo.getPoint()
        );
    }
}
