package com.example.chapter4.global.validator;

import com.example.chapter4.global.annotation.CheckPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 조건: 0 또는 음수면 에러 (프론트는 1부터 시작)
        if (value != null && value < 1) {
            // 유효성 검사 실패 -> false 반환 -> MethodArgumentNotValidException 발생
            // -> RestControllerAdvice에서 처리됨
            return false;
        }
        return true;
    }
}
