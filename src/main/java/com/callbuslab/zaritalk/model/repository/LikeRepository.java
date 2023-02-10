package com.callbuslab.zaritalk.model.repository;

import com.callbuslab.zaritalk.model.entity.Like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

}
