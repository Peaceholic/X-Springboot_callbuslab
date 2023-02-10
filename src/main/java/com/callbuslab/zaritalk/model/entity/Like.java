package com.callbuslab.zaritalk.model.entity;

import com.callbuslab.zaritalk.base.entity.BaseEntity;
import com.callbuslab.zaritalk.model.dto.LikeDto;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Like extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")

    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
    public static LikeDto.Base response(Like like) {

        LikeDto.Base likeApiResponse = LikeDto.Base.builder()
                .id(like.getId())
                .user(like.getUser())
                .board(like.getBoard())
                .build();

        return likeApiResponse;
    }

    public Like dtoToEntity(LikeDto.Base request) {
        return Like.builder()
                .user(request.getUser())
                .board(request.getBoard())
                .build();
    }

}
