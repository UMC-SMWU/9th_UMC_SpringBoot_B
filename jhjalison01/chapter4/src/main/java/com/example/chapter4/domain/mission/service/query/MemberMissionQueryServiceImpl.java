package com.example.chapter4.domain.mission.service.query;


import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.member.exception.MemberException;
import com.example.chapter4.domain.member.exception.code.MemberErrorCode;
import com.example.chapter4.domain.member.repository.MemberRepository;
import com.example.chapter4.domain.mission.converter.MemberMissionConverter;
import com.example.chapter4.domain.mission.dto.MissionSearchCondition;
import com.example.chapter4.domain.mission.dto.req.MissionResDto;
import com.example.chapter4.domain.mission.entity.mapping.MemberMission;
import com.example.chapter4.domain.mission.repository.MemberMissionQueryDsl;
import com.example.chapter4.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberMissionQueryDsl memberMissionQueryDsl;

    @Override
    public MissionResDto.MissionPreviewListDto getChallengingMissions(Long memberId, MissionSearchCondition condition) {

        // 회원 존재 유무 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 페이징 설정 (프론트 1-based -> 백엔드 0-based)
        int pageIndex = condition.getPage() - 1;
        Pageable pageable = PageRequest.of(pageIndex, 10);

        // Repository 호출 (진행 중인 미션 조회)
        Page<MemberMission> missionPage = memberMissionQueryDsl.findChallengingMissions(memberId, pageable);

        return MemberMissionConverter.toMissionPreviewListDto(missionPage);
    }

}
