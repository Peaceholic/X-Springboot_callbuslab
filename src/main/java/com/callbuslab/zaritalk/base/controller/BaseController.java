package com.callbuslab.zaritalk.base.controller;


import com.callbuslab.zaritalk.base.inf.ControllerInterface;
import com.callbuslab.zaritalk.base.service.BaseService;
import com.callbuslab.zaritalk.common.HttpHeader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "ERROR")
})
@Component
public abstract class BaseController<Req, Res, Entity> implements ControllerInterface<Req, Res> {

    @Autowired(required = false)
    protected BaseService<Req, Res, Entity> baseService;

    @Override
    @ApiOperation(value = "Domain 데이터 생성")
    @PostMapping("")
    public HttpHeader<Res> create(@RequestBody Req request) {
        return baseService.create(request);
    }

    @Override
    @ApiOperation(value = "Domain 데이터 상세조회")
    @GetMapping("{id}")
    public HttpHeader<Res> read(
            @ApiParam(value = "고유ID", required = true, example = "1")
            @PathVariable Long id
    ) {
        return baseService.read(id);
    }

    @Override
    @ApiOperation(value = "Domain 데이터 수정")
    @PutMapping("")
    public HttpHeader<Res> update(@RequestBody Req request) {
        return baseService.update(request);
    }

    @Override
    @ApiOperation(value = "Domain 데이터 삭제")
    @DeleteMapping("{id}")
    public HttpHeader delete(
            @ApiParam(value = "고유ID", required = true, example = "1")
            @PathVariable Long id
    ) {
        return baseService.delete(id);
    }

    @Override
    @ApiOperation(value = "Domain 데이터 전체조회")
    @GetMapping("")
    public HttpHeader<List<Res>> search(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
        return baseService.search(pageable);
    }
}
