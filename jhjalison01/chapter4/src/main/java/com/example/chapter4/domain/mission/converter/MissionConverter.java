package com.example.chapter4.domain.mission.converter;

import com.example.chapter4.domain.mission.dto.req.MissionResDto;
import com.example.chapter4.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MissionResDto.MissionDetailDto toMissionDetailDto(Mission mission) {
        return MissionResDto.MissionDetailDto.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .content(mission.getContent())
                .point(mission.getPoint())
                .deadline(mission.getDeadline().toString())
                .build();
    }

    public static MissionResDto.MissionPreviewListDto toMissionPreviewListDto(Page<Mission> missionPage) {

        List<MissionResDto.MissionDetailDto> missionList = missionPage.stream()
                .map(MissionConverter::toMissionDetailDto)
                .collect(Collectors.toList());

        return MissionResDto.MissionPreviewListDto.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionList.size())
                .missionList(missionList)
                .build();
    }
}
