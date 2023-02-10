package com.callbuslab.zaritalk.model.dto.response;

import com.callbuslab.zaritalk.model.enums.Authority;
import com.callbuslab.zaritalk.model.enums.UserStatus;

import org.springframework.format.annotation.NumberFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


public class UserResponse {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Base {

        @ApiModelProperty(value = "고유 id", hidden = true)
        @Id
        private Long id;

        @ApiModelProperty(value = "로그인 아이디", required = true, example = "test")
        @NotEmpty
        private String account;

        @ApiModelProperty(value = "비밀번호", required = true, example = "1111")
        @NotEmpty
        private String password;

        @ApiModelProperty(value = "등록상태", required = true, example = "REGISTERED")
        @NotEmpty
        private UserStatus status;

        @ApiModelProperty(value = "권한", required = true, example = "ROLE_USER")
        @NotEmpty
        private Authority authority;

        @ApiModelProperty(value = "이름", required = true, example = "홍길동")
        @NotEmpty
        private String name;

        @ApiModelProperty(value = "이메일", example = "test@gmail.com")
        @Email
        private String email;

        @ApiModelProperty(value = "전화번호", example = "01011112222")
        @NumberFormat
        private String phoneNumber;

        @ApiModelProperty(value = "사용일", hidden = true)
        private LocalDateTime registeredAt;

        @ApiModelProperty(value = "해제일", hidden = true)
        private LocalDateTime unregisteredAt;
    }
}
