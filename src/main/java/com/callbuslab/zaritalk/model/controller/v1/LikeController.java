package com.callbuslab.zaritalk.model.controller.v1;

import com.callbuslab.zaritalk.base.controller.BaseController;
import com.callbuslab.zaritalk.model.dto.LikeDto;
import com.callbuslab.zaritalk.model.entity.Like;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"4. 게시판 Like [Table -  like]"})
@Slf4j
@RestController
@RequestMapping("/v1/api/like")

public class LikeController extends BaseController<LikeDto.Base, LikeDto.Base, Like> {

}
