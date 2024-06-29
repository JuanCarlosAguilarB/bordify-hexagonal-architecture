package com.bordify.topic.domain;

import com.bordify.board.domain.Board;
import com.bordify.color.domain.Color;
import lombok.*;

import java.util.UUID;


@Builder
@AllArgsConstructor
@Getter
@Setter
public class Topic {

    private UUID    id;
    private String  name;
    private Board board;
    private Color color;

}
