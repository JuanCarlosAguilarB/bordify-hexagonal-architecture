package com.bordify.domain.models;

import com.bordify.board.domain.Board;
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
    private Color   color;

}
