package com.callbuslab.zaritalk.model.repository;

import com.callbuslab.zaritalk.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    Optional<User> findByEmail(String name);

    boolean existsByEmail(String email);
}
