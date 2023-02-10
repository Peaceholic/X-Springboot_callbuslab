package com.callbuslab.zaritalk.model.repository;

import com.callbuslab.zaritalk.CallbuslabApplicationTests;
import com.callbuslab.zaritalk.model.entity.User;
import com.callbuslab.zaritalk.model.enums.AccountType;
import com.callbuslab.zaritalk.model.enums.Quit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest extends CallbuslabApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){

          User user =  User.builder()
                .nickname("test1")
                .name("김철수")
                .accountType(AccountType.LESSOR)
                .accountId("test2")
                .password("1111")
                .quit(Quit.REGISTERED)
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        assertNotNull(newUser);
    }
}
