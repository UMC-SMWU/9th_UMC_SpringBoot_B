package com.example.chapter4.domain.member.repository;

import com.example.chapter4.domain.member.dto.MyPageInfoDto;
import com.example.chapter4.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //마이페이지 정보 조회
    @Query("SELECT new com.example.chapter4.domain.member.dto.MyPageInfoDto(m.name, m.email, m.phoneNum, m.point) FROM Member m WHERE m.id = :memberId")
    MyPageInfoDto findMyPageInfoById(@Param("memberId") Long memberId);

    Optional<Member> findByEmail(String email);
}