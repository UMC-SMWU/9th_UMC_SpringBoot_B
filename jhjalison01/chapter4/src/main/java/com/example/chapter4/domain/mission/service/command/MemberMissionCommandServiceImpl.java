package com.example.chapter4.domain.mission.service.command;

import com.example.chapter4.domain.member.entity.Member;
import com.example.chapter4.domain.member.exception.MemberException;
import com.example.chapter4.domain.member.exception.code.MemberErrorCode;
import com.example.chapter4.domain.member.repository.MemberRepository;
import com.example.chapter4.domain.mission.converter.MemberMissionConverter;
import com.example.chapter4.domain.mission.dto.req.MemberMissionRequestDto;
import com.example.chapter4.domain.mission.entity.Mission;
import com.example.chapter4.domain.mission.entity.mapping.MemberMission;
import com.example.chapter4.domain.mission.exception.MissionException;
import com.example.chapter4.domain.mission.exception.code.MissionErrorCode;
import com.example.chapter4.domain.mission.repository.MemberMissionRepository;
import com.example.chapter4.domain.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService{
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    public MemberMission createMemberMission(MemberMissionRequestDto.CreateDto request, Long memberId) {

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        if (memberMissionRepository.existsByMemberIdAndMissionId(memberId, mission.getId())) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGED);
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 매핑 엔티티 생성 (Converter 활용)
        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);

        return memberMissionRepository.save(memberMission);
    }
}
