package com.example.chapter4.domain.mission.service.query;

import com.example.chapter4.domain.mission.converter.MissionConverter;
import com.example.chapter4.domain.mission.dto.ChallengeableMissionDto;
import com.example.chapter4.domain.mission.dto.CompletedMissionDto;
import com.example.chapter4.domain.mission.dto.InProgressMissionDto;
import com.example.chapter4.domain.mission.dto.MissionSearchCondition;
import com.example.chapter4.domain.mission.dto.req.MissionResDto;
import com.example.chapter4.domain.mission.entity.Mission;
import com.example.chapter4.domain.mission.repository.MemberMissionRepository;
import com.example.chapter4.domain.mission.repository.MissionQueryDsl;
import com.example.chapter4.domain.mission.repository.MissionRepository;
import com.example.chapter4.domain.store.exception.StoreException;
import com.example.chapter4.domain.store.exception.code.StoreErrorCode;
import com.example.chapter4.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MissionQueryDsl missionQueryDsl;

    //진행중인 미션 가져오기
    public List<InProgressMissionDto> getInProgressMissions(Long memberId, Long lastCursorId) {
        // 첫 번째 페이지(0), 사이즈(3)
        Pageable pageable = PageRequest.of(0, 3);

        return memberMissionRepository.findInProgressMissions(memberId, lastCursorId, pageable);
    }

    //완료한 미션 가져오기
    public List<CompletedMissionDto> getCompletedMissions(Long memberId, Long lastCursorId) {
        // 첫 번째 페이지(0), 사이즈(3)
        Pageable pageable = PageRequest.of(0, 3);

        return memberMissionRepository.findCompletedMissions(memberId, lastCursorId, pageable);
    }

    //도전 가능한 미션 가져오기
    public List<ChallengeableMissionDto> getChallengeableMissions(Long memberId, String locationName, Long lastCursorId) {
        // Pageable 객체를 생성하여 LIMIT 3 효과를 줍니다.
        Pageable pageable = PageRequest.of(0, 3);

        return missionRepository.findChallengeableMissions(memberId, locationName, lastCursorId, pageable);
    }

    @Override
    public MissionResDto.MissionPreviewListDto getMissionsByStore(Long storeId, MissionSearchCondition condition) {

        // 가게가 존재하는지 확인
        storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 페이징 변환 (프론트 1 -> 백엔드 0)
        int pageIndex = condition.getPage() - 1;
        Pageable pageable = PageRequest.of(pageIndex, 10);

        // QueryDSL Repository 호출
        Page<Mission> missionPage = missionQueryDsl.findMissionsByStoreId(storeId, pageable);

        return MissionConverter.toMissionPreviewListDto(missionPage);
    }
}
