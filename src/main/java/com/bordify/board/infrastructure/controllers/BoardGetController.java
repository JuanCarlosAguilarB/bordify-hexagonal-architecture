package com.bordify.board.infrastructure.controllers;

import com.bordify.board.application.find.BoardFinder;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.users.application.find.UserFinder;
import com.bordify.users.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Board", description = "Board management operations")
public class BoardGetController {

    private final BoardFinder boardFinder;
    private final UserFinder userFinder;

    /**
     * List all boards.
     *
     * @param pageable The pagination information.
     * @param auth The authentication object containing information about the authenticated user.
     * @return A ResponseEntity with the list of boards.
     */
    @GetMapping("/v1/boards/")
    @Operation(summary = "List boards", description = "List all boards", tags = { "Board" })
    public ResponseEntity<?> listBoards(Pageable pageable, Authentication auth) {

        String username = auth.getName();

        User user = userFinder.findUserByUsername(username);

        PaginationRequest paginationRequest = new PaginationRequest(pageable.getPageNumber(), pageable.getPageSize());

        return ResponseEntity.ok(boardFinder.findAllBoards(paginationRequest, user.getId()));

    }


}
