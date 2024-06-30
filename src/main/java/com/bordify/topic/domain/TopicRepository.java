package com.bordify.topic.domain;

import com.bordify.shared.domain.PaginationRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing and managing topic entities in the database.
 */
public interface TopicRepository {

    /**
     * Retrieves a list of topic DTOs by boardEntity ID with custom projection and pagination.
     *
     * @param boardId The ID of the boardEntity.
     * @param pageable The pagination information.
     * @return A list of topic DTOs associated with the specified boardEntity ID.
     */
    List<TopicListDTO> findByBoardIdCustom(UUID boardId, PaginationRequest pageable);

    /**
     * Saves a topic entity to the database.
     * @param topic
     */
    public void save(Topic topic);

    public void deleteById(UUID id);

    public Optional<Topic> findById(UUID id);
}