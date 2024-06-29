package com.bordify.board.application;


import com.bordify.board.domain.BoardListDTO;
import com.bordify.board.infrastructure.persistence.BoardEntity;
import com.bordify.board.infrastructure.persistence.BoardJpaRepository;
import com.bordify.exceptions.EntityNotFound;
import com.bordify.exceptions.ResourceNotCreatedException;
import com.bordify.utils.UpdateFieldsOfClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing board operations.
 * This service handles creation, deletion, updating, and listing of boards.
 */
@Service
public class BoardService {

    @Autowired
    private BoardJpaRepository boardRepository;



    /**
     * Updates an existing board.
     *
     * @param board The updated board entity.
     * @return The updated board entity with selective information.
     * @throws EntityNotFound if the board to update does not exist.
     */
    public BoardEntity update(BoardEntity board) {
        ensureBoardExist(board);

        BoardEntity boardToUpdate = boardRepository.findById(board.getId()).get();

        UpdateFieldsOfClasses.updateFields(boardToUpdate, board);

        boardRepository.save(boardToUpdate);

        return BoardEntity.builder()
                .id(boardToUpdate.getId())
                .name(boardToUpdate.getName())
                .build();
    }

    /**
     * Ensures that a board exists.
     *
     * @param board The board to check existence for.
     * @throws EntityNotFound if the board does not exist.
     */
    public void ensureBoardExist(BoardEntity boardEntity) {
        if (!boardRepository.existsById(boardEntity.getId())) {
            throw new EntityNotFound("Board not found");
        }
    }

    /**
     * Checks if a user is the owner of a board.
     *
     * @param boardId The ID of the board to check ownership for.
     * @param userId The ID of the user to check ownership against.
     * @return True if the user is the owner of the board, false otherwise.
     * @throws EntityNotFound if the board with the specified ID does not exist.
     */
    public Boolean isUserOwnerOfBoard(UUID boardId, UUID userId) {
        Optional<BoardEntity> board = Optional.ofNullable(boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFound("Board not found")));
        return board.get().getUserId().equals(userId);
    }


}