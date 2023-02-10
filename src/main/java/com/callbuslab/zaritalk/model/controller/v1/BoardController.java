package com.callbuslab.zaritalk.model.controller.v1;

import com.callbuslab.zaritalk.common.HttpHeader;
import com.callbuslab.zaritalk.model.dto.request.TokenRequest;
import com.callbuslab.zaritalk.model.dto.request.UserRequest;
import com.callbuslab.zaritalk.model.dto.response.TokenResponse;
import com.callbuslab.zaritalk.model.dto.response.UserResponse;
import com.callbuslab.zaritalk.model.serviceImpl.v1.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"2. 인증"})
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public HttpHeader<UserResponse.Base> signup(@RequestBody UserRequest.Base request) {
        return authService.signup(request);
    }

    @ApiOperation(value = "로그인 & 토큰 생성")
    @PostMapping("/login")
    public HttpHeader<TokenResponse> login(@RequestBody UserRequest.Auth request) {
        return authService.login(request);
    }

    @ApiOperation(value = "재발행 시간을 통한 토큰 재생성")
    @PostMapping("/reissue")
    public HttpHeader<TokenResponse> reissue(@RequestBody TokenRequest request) {
        return authService.reissue(request);
    }
}
