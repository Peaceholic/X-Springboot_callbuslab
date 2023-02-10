package com.callbuslab.zaritalk.model.controller.v1;

import com.callbuslab.zaritalk.base.controller.BaseController;
import com.callbuslab.zaritalk.model.dto.request.UserRequest;
import com.callbuslab.zaritalk.model.dto.response.UserResponse;
import com.callbuslab.zaritalk.model.entity.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api(tags = {"1. 사용자 [Table - User]"})
@Slf4j
@RestController
@RequestMapping("/v1/api/user")
//@Validated param validation check 할 때 사용
public class UserController extends BaseController<UserRequest.Base, UserResponse.Base, User> {

}
