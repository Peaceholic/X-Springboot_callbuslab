package com.callbuslab.zaritalk.model.dto;

import com.callbuslab.zaritalk.model.entity.Board;
import com.callbuslab.zaritalk.model.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class LikeDto {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Base {

        @ApiModelProperty(value = "고유 id", hidden = true)
        @Id
        private Long id;

        @ApiModelProperty(value = "User", required = true, example = "Lessor1")
        @NotEmpty
        private User user;

        @ApiModelProperty(value = "게시글", required = true, example = "1")
        @NotEmpty
        private Board board;

    }
}
