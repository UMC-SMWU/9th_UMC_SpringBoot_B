package com.example.chapter4.domain.mission.repository;

import com.example.chapter4.domain.mission.entity.Mission;
import com.example.chapter4.domain.mission.entity.QMission;
import com.example.chapter4.domain.mission.entity.mapping.MemberMission;
import com.example.chapter4.domain.mission.entity.mapping.QMemberMission;
import com.example.chapter4.domain.store.entity.QStore;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionQueryDslImpl implements MemberMissionQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MemberMission> findChallengingMissions(Long memberId, Pageable pageable) {


        QMemberMission memberMission = QMemberMission.memberMission;
        QMission mission = QMission.mission;
        QStore store = QStore.store; // 가게 이름도 가져와야 하므로

        List<MemberMission> content = queryFactory
                .selectFrom(memberMission)
                .join(memberMission.mission, mission).fetchJoin() // 미션 정보 로딩
                .join(mission.store, store).fetchJoin()           // 가게 정보 로딩
                .where(
                        memberMission.member.id.eq(memberId),
                        memberMission.isComplete.eq(false) // 진행 중인 상태 (완료 안 됨)
                )
                .orderBy(mission.deadline.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(memberMission.count())
                .from(memberMission)
                .where(
                        memberMission.member.id.eq(memberId),
                        memberMission.isComplete.eq(false)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }

}
