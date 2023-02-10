package com.callbuslab.zaritalk.model.entity;

import com.callbuslab.zaritalk.base.entity.BaseEntity;
import com.callbuslab.zaritalk.model.dto.BoardDto;

import com.callbuslab.zaritalk.model.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String writer;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    public static BoardDto.Base response(Board board) {

        BoardDto.Base boardApiResponse = BoardDto.Base.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createDate(board.getRegisteredAt())
                .lastModifiedDate(board.getUnregisteredAt())
                .build();

        return boardApiResponse;
    }

    public Board dtoToEntity(BoardDto.Base request) {
        return Board.builder()
                .id(request.getId())
                .title(request.getTitle())
                .content(request.getContent())
                .writer(request.getWriter())
                .build();
    }
}
