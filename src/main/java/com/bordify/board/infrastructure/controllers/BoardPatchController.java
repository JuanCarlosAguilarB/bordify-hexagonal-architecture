package com.bordify.board.infrastructure.controllers;

import com.bordify.board.application.update.BoardUpdater;
import com.bordify.board.infrastructure.persistence.BoardEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@Tag(name = "Board", description = "Board management operations")
@AllArgsConstructor
public class BoardPatchController {

    private final BoardUpdater  boardUpdater;

    /**
     * Handle a partial update of a board.
     *
     * @param id The id of the board to update.
     * @param boardRequest The request body containing the updated board data.
     * @return A ResponseEntity with the updated board.
     */
    @PatchMapping("/v1/boards/{id}/")
    public ResponseEntity<?> handler(@PathVariable UUID id, @RequestBody Map<String, Object> boardRequest) {

        boardUpdater.update(id, boardRequest);

        return ResponseEntity.ok().build();
    }


}
