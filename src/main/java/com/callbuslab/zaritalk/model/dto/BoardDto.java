package com.callbuslab.zaritalk.model.dto;

import com.callbuslab.zaritalk.model.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class Board {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Base {

        @ApiModelProperty(value = "고유 id", hidden = true)
        @Id
        private Long id;

        @ApiModelProperty(value = "제목", required = true, example = "제목 test")
        @NotEmpty
        private String title;

        @ApiModelProperty(value = "본문내용", required = true, example = "내용 test")
        @NotEmpty
        private String content;

        @ApiModelProperty(value = "작성자", required = true, example = "REALTOR1")
        @NotEmpty
        private User writer;

        @CreatedDate
        @Column(updatable = false)
        private LocalDateTime createDate;

        @LastModifiedDate
        private LocalDateTime lastModifiedDate;

    }
}
