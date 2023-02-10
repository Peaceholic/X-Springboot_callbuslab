package com.callbuslab.zaritalk.base.service;

import com.callbuslab.zaritalk.base.inf.ServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseService<Req, Res, Entity> implements ServiceInterface<Req, Res> {

    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;

    public void setBaseRepository(JpaRepository<Entity, Long> baseRepository) {
        this.baseRepository = baseRepository;
    }
}
