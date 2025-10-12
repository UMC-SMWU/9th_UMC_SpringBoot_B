package com.example.umc9th.domain.store.dto;

import com.example.umc9th.domain.store.enums.Address;

public record HomeMissionItem(
        Long missionId, String title, String storeName, Address address
) {}
