package com.callbuslab.zaritalk.model.repository;

import com.callbuslab.zaritalk.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByAccountId(String accountId);

    Optional<Board> findByEmail(String name);


}
