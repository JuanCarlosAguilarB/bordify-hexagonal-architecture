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
     * Deletes a board by its id.
     *
     * @param boardId The id of the board to be deleted.
     * @return A ResponseEntity with no content.
     */
    @Operation(summary = "Delete a board", description = "Deletes a board by its id", tags = { "Board" })
    @DeleteMapping("/boards/{boardId}/")
    public ResponseEntity<?> deleteBoard(@PathVariable UUID boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok().build();
    }


    /**
     * Get all topics of a board.
     *
     * @param boardId The id of the board to retrieve topics for.
     * @param pageable The pagination information.
     * @param auth The authentication object containing information about the authenticated user.
     * @return A ResponseEntity with the topics of the board.
     */
    @Operation(summary = "Get all topics of a board",
            description = "Lists all topics of a board for a given board",
            tags = { "Board" })
    @GetMapping("/boards/{boardId}/topics/")
    public ResponseEntity<?> getTopicsOfBoard(
            @PathVariable UUID boardId,
            Pageable pageable,
            Authentication auth) {


        // verify that owner of the board is the one requesting the topics
        String username = auth.getName();
        User user = userFinder.findUserByUsername(username);

        boolean isUserOwnerOfBoard = boardService.isUserOwnerOfBoard(boardId, user.getId());

        if (!isUserOwnerOfBoard){

            ApiExceptionResponse response = ApiExceptionResponse.builder()
                    .status(HttpStatus.FORBIDDEN.value())
                    .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                    .message("User is not the owner of the board")
                    .build();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        Page<TopicListDTO> topics = topicService.getTopicsOfBoard(boardId, pageable);



        return ResponseEntity.ok(topics);
    }

    /**
     * List all boards.
     *
     * @param pageable The pagination information.
     * @param auth The authentication object containing information about the authenticated user.
     * @return A ResponseEntity with the list of boards.
     */
    @GetMapping("/boards/")
    @Operation(summary = "List boards", description = "List all boards", tags = { "Board" })
    public ResponseEntity<?> listBoards(Pageable pageable, Authentication auth) {

        String username = auth.getName();

        User user = userFinder.findUserByUsername(username);

        return ResponseEntity.ok(boardService.listBoards(pageable, user.getId()));
    }

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


    /**
     * Update a board.
     *
     * @param id The id of the board to update.
     * @param boardRequest The request body containing the updated board data.
     * @return A ResponseEntity with the updated board.
     */
    @PutMapping("/boards/{id}/")
    public ResponseEntity<?> updateBoard(@PathVariable UUID id, @RequestBody BoardRequest boardRequest) {

        BoardEntity boardEntity = new BoardEntity().builder()
                .id(id)
                .name(boardRequest.getName())
                .build();

        BoardEntity boardEntityUpdated = boardService.update(boardEntity);

        return ResponseEntity.ok(boardEntityUpdated);
    }

}
