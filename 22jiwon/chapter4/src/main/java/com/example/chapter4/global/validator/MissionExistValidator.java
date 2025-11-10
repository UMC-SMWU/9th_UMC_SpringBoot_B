package com.example.chapter4.global.validator;

import com.example.chapter4.domain.mission.code.MissionErrorCode;
import com.example.chapter4.domain.mission.repository.MissionRepository;
import com.example.chapter4.global.annotation.ExistMission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MissionExistValidator implements ConstraintValidator<ExistMission, Long> {

    private final MissionRepository missionRepository;

    @Override
    public void initialize(ExistMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isValid = missionRepository.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MissionErrorCode.NOT_FOUND.getMessage())
                    .addConstraintViolation();
        }

        return isValid;
    }
}