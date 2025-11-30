package com.example.chapter4.domain.mission.repository;

import com.example.chapter4.domain.mission.entity.Mission;
import com.example.chapter4.domain.mission.entity.QMission;
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
public class MissionQueryDslImpl implements MissionQueryDsl{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Mission> findMissionsByStoreId(Long storeId, Pageable pageable) {

        QMission mission = QMission.mission;
        QStore store = QStore.store;

        List<Mission> content = queryFactory
                .selectFrom(mission)
                .join(mission.store, store).fetchJoin()
                .where(store.id.eq(storeId))
                //.orderBy(mission.deadline.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        Long total = queryFactory
                .select(mission.count())
                .from(mission)
                .where(store.id.eq(storeId))
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }
}
