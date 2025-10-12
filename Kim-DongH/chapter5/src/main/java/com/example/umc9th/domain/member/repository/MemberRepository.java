package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.dto.MyPageSummary;
import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // soft delete 미적용 + 요약 정보를 한 번에
    @Query("""
    select new com.example.umc9th.domain.member.dto.MyPageSummary(
        m.id, m.name, m.email, m.point,
        (select count(r) from Review r where r.member.id = m.id),
        (select count(distinct mf.food.id) from MemberFood mf where mf.member.id = m.id)
    )
    from Member m
    where m.id = :memberId and m.deletedAt is null
    """)
    Optional<MyPageSummary> loadMyPageSummary(@Param("memberId") Long memberId);

    Optional<Member> findByNameAndDeletedAtIsNull(String name);
}

