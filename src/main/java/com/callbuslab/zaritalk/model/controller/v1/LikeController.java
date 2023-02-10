package com.callbuslab.zaritalk.model.controller.v1;

import com.callbuslab.zaritalk.base.controller.BaseController;
import com.callbuslab.zaritalk.model.dto.BoardDto;
import com.callbuslab.zaritalk.model.entity.Board;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api(tags = {"3. 게시판 [Table -  board]"})
@Slf4j
@RestController
@RequestMapping("/v1/api/board")

public class BoardController extends BaseController<BoardDto.Base, BoardDto.Base, Board> {

}
