package com.callbuslab.zaritalk.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Quit {

    REGISTERED(0, "등록", "사용자 등록상태")
    , UNREGISTERED(1, "해제", "사용자 해제상태")
    ;

    private Integer id;
    private String title;
    private String description;
}
