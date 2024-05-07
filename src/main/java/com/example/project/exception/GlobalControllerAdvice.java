package com.example.project.exception;

import com.example.project.exception.RuntimeException.ProjectException;
import com.example.project.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
    }


    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ErrorResponse> exception(ProjectException e) {
        log.error("[Project Runtime Error] : {}", e.toString());

        int statusCode = e.getStatusCode();

        var body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode)
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception e) {
        log.error("[Internal Sever Error] : {}", e.toString());

        var body = ErrorResponse.builder()
                .code("500")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(500)
                .body(body);
    }
}
