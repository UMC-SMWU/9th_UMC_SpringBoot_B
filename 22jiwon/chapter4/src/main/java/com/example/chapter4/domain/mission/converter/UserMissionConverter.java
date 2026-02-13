package com.example.chapter4.domain.mission.converter;

import com.example.chapter4.domain.mission.dto.UserMissionListResponseDto;
import com.example.chapter4.domain.mission.entity.UserMissionCompletion;
import org.springframework.data.domain.Page;

public class UserMissionConverter {

    public static UserMissionListResponseDto.UserMissionPreviewDto toUserMissionPreviewDto(
            UserMissionCompletion umc
    ){
        var m = umc.getMission();
        return UserMissionListResponseDto.UserMissionPreviewDto.builder()
                .missionId(m.getId())
                .missionName(m.getName())
                .storeName(m.getStore().getName())
                .pointAwarded(m.getPointAwarded())
                .deadline(m.getDeadline())
                .status(umc.getStatus())
                .challengedAt(umc.getChallengedAt())
                .build();
    }

    public static UserMissionListResponseDto.UserMissionPreviewListDto toUserMissionPreviewListDto(
            Page<UserMissionCompletion> page
    ){
        return UserMissionListResponseDto.UserMissionPreviewListDto.builder()
                .content(
                        page.getContent().stream()
                                .map(UserMissionConverter::toUserMissionPreviewDto)
                                .toList()
                )
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
