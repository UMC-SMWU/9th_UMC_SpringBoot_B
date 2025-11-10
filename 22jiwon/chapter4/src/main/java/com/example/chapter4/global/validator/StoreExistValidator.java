package com.example.chapter4.global.validator;

import com.example.chapter4.domain.store.repository.StoreRepository;
import com.example.chapter4.global.annotation.ExistStore;
import com.example.chapter4.domain.store.code.StoreErrorCode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreRepository storeRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isValid = storeRepository.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    StoreErrorCode.NOT_FOUND.getMessage()
            ).addConstraintViolation();
        }

        return isValid;
    }
}
