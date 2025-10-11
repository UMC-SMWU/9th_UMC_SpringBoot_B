package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.member.entity.Member;
import jakarta.persistence.*;

@Entity
@Table(name = "member_mission",
        uniqueConstraints = @UniqueConstraint(name="uk_member_mission", columnNames = {"member_id","mission_id"}),
        indexes = {
                @Index(name="idx_member_mission_member", columnList="member_id"),
                @Index(name="idx_member_mission_mission", columnList="mission_id")
        })
public class MemberMission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMissionId;

    @Column(nullable = false)
    private Boolean isComplete = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_id", nullable = false)
    private com.example.umc9th.domain.member.entity.Member member;

}
