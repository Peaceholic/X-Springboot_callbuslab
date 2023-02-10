package com.callbuslab.zaritalk.common;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unchecked")
public class HttpHeader<T> {

    // api 통신시간
    private LocalDateTime transactionTime;

    // api 응답 코드
    private String resultCode;

    // api 응답 내용
    private String resultReasonPhrase;

    // api 부가 설명
    private String description;

    //  @Valid
    private T data;

    // pagination
    private Pagination pagination;

    // OK
    public static <T> HttpHeader<T> OK() {
        return (HttpHeader<T>) HttpHeader.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultReasonPhrase(HttpStatus.OK.getReasonPhrase())
                .description(HttpStatus.OK.toString())
                .build();
    }


    // DATA OK
    public static <T> HttpHeader<T> OK(T data) {
        return (HttpHeader<T>) HttpHeader.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultReasonPhrase(HttpStatus.OK.getReasonPhrase())
                .description(HttpStatus.OK.toString())
                .data(data)
                .build();
    }

    public static <T> HttpHeader<T> OK(T data, Pagination pagination) {
        return (HttpHeader<T>) HttpHeader.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultReasonPhrase(HttpStatus.OK.getReasonPhrase())
                .description(HttpStatus.OK.toString())
                .data(data)
                .pagination(pagination)
                .build();
    }

    // ERROR
    public static <T> HttpHeader<T> ERROR(String description) {
        return (HttpHeader<T>) HttpHeader.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .resultReasonPhrase(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .description(description)
                .build();
    }
}
