package com.bordify.domain.ports.out;

import com.bordify.domain.models.dtos.TopicListDTO;
import com.bordify.shared.domain.PageRequest;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for accessing and managing topic entities in the database.
 */
public interface TopicRepository {

    /**
     * Retrieves a list of topic DTOs by board ID with custom projection and pagination.
     *
     * @param boardId The ID of the board.
     * @param pageable The pagination information.
     * @return A list of topic DTOs associated with the specified board ID.
     */
    List<TopicListDTO> findByBoardIdCustom(UUID boardId, PageRequest pageable);
}