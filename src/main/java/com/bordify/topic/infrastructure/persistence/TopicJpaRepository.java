package com.bordify.topic.infrastructure.persistence;


import com.bordify.topic.domain.TopicListDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for accessing and managing topicEntity entities in the database.
 */
public interface TopicJpaRepository extends JpaRepository<TopicEntity, UUID> {

    /**
     * Retrieves a list of topicEntity DTOs by boardEntity ID with custom projection and pagination.
     *
     * @param boardId The ID of the boardEntity.
     * @param pageable The pagination information.
     * @return A list of topicEntity DTOs associated with the specified boardEntity ID.
     */
    @Query("SELECT new com.bordify.topic.domain.TopicListDTO(t.id, t.name, c) FROM TopicEntity t LEFT JOIN t.colorEntity c WHERE t.boardId = :boardId")
    List<TopicListDTO> findByBoardIdCustom(UUID boardId, Pageable pageable);
}