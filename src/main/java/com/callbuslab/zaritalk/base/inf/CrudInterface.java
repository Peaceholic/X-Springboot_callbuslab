package com.callbuslab.zaritalk.base.inf;

import com.callbuslab.zaritalk.common.HttpHeader;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudInterface<Req, Res> {

    HttpHeader<Res> create(Req request);

    HttpHeader<Res> read(Long id);

    HttpHeader<Res> update(Req request);

    HttpHeader delete(Long id);

    HttpHeader<List<Res>> search(Pageable pageable);

}
