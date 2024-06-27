package com.bordify.domain.ports.out;

import com.bordify.domain.models.Board;
import com.bordify.domain.models.dtos.BoardListDTO;

import com.bordify.shared.domain.PageRequest;
import com.bordify.shared.domain.PageResult;

import java.util.UUID;

/**
 * Repository interface for accessing and managing board entities in the database.
 */
public interface BoardRepository {

    /**
     * Deletes a board by its ID.
     *
     * @param boardId The ID of the board to delete.
     */
    public void deleteById(UUID boardId);

    /**
     * Checks if a board exists by its ID.
     *
     * @param boardId The ID of the board to check.
     * @return True if the board exists, false otherwise.
     */
    public boolean existsById(UUID boardId);

    /**
     * Retrieves a page of board DTOs.
     *
     * @param pageable The pagination information.
     * @return A PageResult of board DTOs.
     */
    public PageResult<BoardListDTO> findBy(PageRequest pageable);

    /**
     * Retrieves a page of board DTOs filtered by user ID.
     *
     * @param pageable The pagination information.
     * @param userId The ID of the user.
     * @return A PageResult of board DTOs filtered by user ID.
     */
    public PageResult<BoardListDTO> findByUserId(PageRequest pageable, UUID userId);

    /**
     * Finds a board DTO by its ID.
     *
     * @param boardId The ID of the board.
     * @return The board DTO.
     */
    public Board findDtoById(UUID boardId);
}
