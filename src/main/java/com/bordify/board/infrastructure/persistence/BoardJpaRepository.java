package com.bordify.board.infrastructure.persistence;

import com.bordify.board.domain.BoardListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing and managing boardEntity entities in the database.
 */
public interface BoardJpaRepository extends JpaRepository<BoardEntity, UUID> {

    /**
     * Deletes a boardEntity by its ID.
     *
     * @param boardId The ID of the boardEntity to delete.
     */
    public void deleteById(UUID boardId);

    /**
     * Checks if a boardEntity exists by its ID.
     *
     * @param boardId The ID of the boardEntity to check.
     * @return True if the boardEntity exists, false otherwise.
     */
    public boolean existsById(UUID boardId);

    /**
     * Retrieves a page of boardEntity DTOs.
     *
     * @param pageable The pagination information.
     * @return A page of boardEntity DTOs.
     */
    public Page<BoardListDTO> findBy(Pageable pageable);

    /**
     * Retrieves a page of boardEntity DTOs filtered by user ID.
     *
     * @param pageable The pagination information.
     * @param userId The ID of the user.
     * @return A page of boardEntity DTOs filtered by user ID.
     */
    public Page<BoardListDTO> findByUserId(Pageable pageable, UUID userId);

    /**
     * Finds a boardEntity DTO by its ID.
     *
     * @param boardId The ID of the boardEntity.
     * @return The boardEntity DTO.
     */
    public BoardEntity findDtoById(UUID boardId);

    public Optional<BoardEntity> findBoardEntityById(UUID boardId);

}
