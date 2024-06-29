package com.bordify.board.application.find;

import com.bordify.board.domain.Board;
import com.bordify.board.domain.BoardListDTO;
import com.bordify.board.domain.BoardRepository;
import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BoardFinder {

    private final BoardRepository  boardRepository;

    /**
     * Lists all boards for a given user.
     *
     * @param pageable Pagination information.
     * @param userId  The UUID of the user whose boards to list.
     * @return A page of {@link BoardListDTO} objects.
     */
    public PageResult<BoardListDTO> findAllBoards(PaginationRequest pageable, UUID userId) {
        return boardRepository.findByUserId(pageable, userId);
    }

    public Board findBoardById(UUID boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board not found"));
    }

}
