package com.bordify.task.infrastructure.persistence;

import com.bordify.task.domain.TaskListDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Repository interface for accessing and managing task entities in the database.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    /**
     * Retrieves a list of task DTOs by topicEntity ID with pagination.
     *
     * @param id The ID of the topicEntity.
     * @param pageable The pagination information.
     * @return A list of task DTOs associated with the specified topicEntity ID.
     */
    public List<TaskListDTO> findByTopicId(UUID id, Pageable pageable);

    /**
     * Retrieves a task by its ID.
     *
     * @param id The ID of the task.
     * @return An Optional containing the task if found, or empty otherwise.
     */
    public Optional<Task> findById(UUID id);
}