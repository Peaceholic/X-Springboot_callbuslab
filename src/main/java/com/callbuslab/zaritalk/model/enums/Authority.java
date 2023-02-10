package com.callbuslab.zaritalk.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {

    ROLE_USER(0, "사용자")
    , ROLE_MANAGER(1, "매니저")
    , ROLE_ADMIN(2, "관리자")
    , ROLE_SUPER_ADMIN(3, "슈퍼 관리자")
    ;

    private Integer id;
    private String name;
}
