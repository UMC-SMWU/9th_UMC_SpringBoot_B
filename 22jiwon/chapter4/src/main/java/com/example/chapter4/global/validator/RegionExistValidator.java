package com.example.chapter4.global.validator;

import com.example.chapter4.domain.region.code.RegionErrorCode;
import com.example.chapter4.domain.region.repository.RegionRepository;
import com.example.chapter4.global.annotation.ExistRegion;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionExistValidator implements ConstraintValidator<ExistRegion, Long> {

    private final RegionRepository regionRepository;

    @Override
    public void initialize(ExistRegion constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        // regionId가 null이면 검증 통과 (다른 @NotNull 등으로 잡아야 함)
        if (value == null) {
            return true;
        }

        boolean isValid = regionRepository.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(RegionErrorCode.NOT_FOUND.getMessage())
                    .addConstraintViolation();
        }

        return isValid;
    }
}