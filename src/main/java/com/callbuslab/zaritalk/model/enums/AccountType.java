package com.callbuslab.zaritalk.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {

    REALTOR(0, "공인중개사")
    , LESSOR(1, "임대인")
    , LESSEE(2, "임차인")
    ;

    private Integer id;
    private String name;
}
