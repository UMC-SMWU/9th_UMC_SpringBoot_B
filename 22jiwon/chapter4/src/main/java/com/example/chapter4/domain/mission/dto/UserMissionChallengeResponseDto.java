package com.example.chapter4.domain.mission.dto;

import com.example.chapter4.domain.mission.entity.UserMissionCompletion;
import com.example.chapter4.domain.mission.entity.UserMissionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserMissionChallengeResponseDto {
    private Long userMissionId;
    private Long userId;
    private Long missionId;
    private UserMissionStatus status;
    private LocalDateTime challengedAt;
    private LocalDateTime completedAt;

    public UserMissionChallengeResponseDto(UserMissionCompletion entity) {
        this.userMissionId = entity.getId();
        this.userId = entity.getUser().getId();
        this.missionId = entity.getMission().getId();
        this.status = entity.getStatus();
        this.challengedAt = entity.getChallengedAt();
        this.completedAt = entity.getCompletedAt();
    }
}
