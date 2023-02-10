package com.callbuslab.zaritalk.common.exception;

import com.callbuslab.zaritalk.common.exception.dto.Error;
import com.callbuslab.zaritalk.common.exception.dto.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice(basePackages = "com.callbuslab.zaritalk.model.controller.v1")
public class ApiControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        List<Error> errorList = new ArrayList<>();

        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            String value = fieldError.getRejectedValue().toString();

            log.error("fieldName: {} / message: {} / value: {}", fieldName, message, value);
            Error errorMessage = Error.builder()
                    .field(fieldName)
                    .message(message)
                    .invalidValue(value)
                    .build();

            errorList.add(errorMessage);
        });
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorList(errorList)
                .message("")
                .requestUrl(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .resultCode("FAIL")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
