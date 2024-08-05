package com.smile.wanted_pre_task.global.exception.handler;

import com.smile.wanted_pre_task.global.exception.AlreadyAppliedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 항목 조회 검증 실패 시 404 코드와 함께 NoSuchElementException 에러 처리
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found : " + errorMessage);
    }

    /**
     * 이미 지원한 공고에 중복지원하는 경우 400 코드와 함께 에러 메세지 전달
     */
    @ExceptionHandler(AlreadyAppliedException.class)
    public ResponseEntity<String> handleAlreadyAppliedException(AlreadyAppliedException ex) {
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    /**
     * 요청 인자가 null 값인 경우 400 코드와 함께 NullPointerException 에러 처리
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("필수 입력 값입니다 : " + ex.getMessage());
    }

    /**
     * 요청 data의 유효성 검증 실패 시 400 코드와 함께 MethodArgumentNotValidException 에러 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

}
