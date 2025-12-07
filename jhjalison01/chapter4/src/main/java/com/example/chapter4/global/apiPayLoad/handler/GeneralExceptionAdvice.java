package com.example.chapter4.global.apiPayLoad.handler;

import com.example.chapter4.global.apiPayLoad.ApiResponse;
import com.example.chapter4.global.apiPayLoad.code.BaseErrorCode;
import com.example.chapter4.global.apiPayLoad.code.GeneralErrorCode;
import com.example.chapter4.global.apiPayLoad.exception.GeneralException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        // 실패한 필드와 메시지를 담을 Map 생성
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        // 앞서 정의한 VALID_FAIL 코드 사용
        GeneralErrorCode code = GeneralErrorCode.VALID_FAIL;

        // 에러 상세 내용(errors Map)을 담아서 응답 생성
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, errors));
    }

    // 애플리케이션에서 발생하는 커스텀 예외를 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            GeneralException ex
    ) {

        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                                ex.getCode(),
                                null
                        )
                );
    }

    /**
     * @CheckPage, @Min 등 유효성 검사 실패 시 발생 (주로 @RequestParam, @PathVariable)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolationException(
            ConstraintViolationException ex
    ) {
        // 에러 메시지 추출 (ex: "페이지 번호는 1 이상이어야 합니다.")
        // ConstraintViolationException은 메시지가 조금 복잡하게 나올 수 있어 파싱하거나 그대로 보여줌
        String errorMessage = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .findFirst()
                .orElse("유효성 검사 실패");

        return ResponseEntity.status(GeneralErrorCode.VALID_FAIL.getStatus())
                .body(ApiResponse.onFailure(
                        GeneralErrorCode.VALID_FAIL,
                        errorMessage // 구체적인 실패 사유 전달
                ));
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex
    ) {

        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                                code,
                                ex.getMessage()
                        )
                );
    }


}
