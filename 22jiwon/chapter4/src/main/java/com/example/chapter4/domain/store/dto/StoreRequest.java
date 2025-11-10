package com.example.chapter4.domain.store.dto;

import com.example.chapter4.global.annotation.ExistRegion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreRequest {

    @NotBlank(message = "가게 이름은 필수입니다.")
    private String name;

    private String managerNumber;

    @NotBlank(message = "상세 주소는 필수입니다.")
    private String detailAddress;

    @ExistRegion
    @NotNull(message = "지역 ID는 필수입니다.")
    private Long regionId;
}