package com.bordify.board.infrastructure.controllers;

import com.bordify.board.infrastructure.persistence.BoardEntity;
import com.bordify.users.application.find.UserFinder;
import com.bordify.users.domain.User;
import com.bordify.dtos.TopicListDTO;
import com.bordify.exceptions.ApiExceptionResponse;
import com.bordify.board.application.BoardService;
import com.bordify.services.TopicService;
import com.bordify.users.application.create.UserCreator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for board management operations.
 */
@RestController
@Tag(name = "Board", description = "Board management operations")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserCreator userCreator;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserFinder userFinder;



    /**
     * Handle a partial update of a board.
     *
     * @param id The id of the board to update.
     * @param boardRequest The request body containing the updated board data.
     * @return A ResponseEntity with the updated board.
     */
    @PatchMapping("/boards/{id}/")
    public ResponseEntity<?> handler(@PathVariable UUID id, @RequestBody BoardRequest boardRequest) {

        BoardEntity boardEntity = new BoardEntity().builder()
                .id(id)
                .name(boardRequest.getName())
                .build();

        BoardEntity boardEntityUpdated = boardService.update(boardEntity);

        return ResponseEntity.ok(boardEntityUpdated);
    }

}
