package com.bordify.board.infrastructure.controllers;


import com.bordify.board.application.create.BoardCreator;
import com.bordify.board.domain.Board;
import com.bordify.users.application.find.UserFinder;
import com.bordify.users.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;


@RestController()
@Tag(name = "Board", description = "Board management operations")
public class BoardPutController {

    private final BoardCreator  boardCreator;
    private final UserFinder userFinder;

    public BoardPutController(BoardCreator boardCreator, UserFinder userFinder) {
        this.boardCreator = boardCreator;
        this.userFinder = userFinder;
    }


    /**
     * Creates a new board for the authenticated user.
     *
     * @param boardRequest The request containing the name of the board to be created.
     * @param auth The authentication object containing information about the authenticated user.
     * @return A ResponseEntity with message the created board.
     */

    @Operation(summary = "Create a new board", description = "Creates a new board for the authenticated user", tags = { "Board" })
    @PutMapping(value = "/v1/boards/{id}/")
    public ResponseEntity<?> create(
            @RequestBody BoardRequest boardRequest,
            Authentication auth,
            @PathVariable UUID id

    ) {

        // Extract userId of token

        // TODO: i dont sure if i should search user here or in the service.

        String username = auth.getName();
        User user = userFinder.findUserByUsername(username);

        Board board = Board.builder()
                .id(id)
                .name(boardRequest.getName())
                .user(user)
                .build();

        boardCreator.createBoard(
                board
        );

        Map<String,String> response = Map.of("message","Board created");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

}
