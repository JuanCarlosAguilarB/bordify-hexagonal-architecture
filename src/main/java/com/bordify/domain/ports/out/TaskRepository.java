package com.bordify.domain.ports.out;

import com.bordify.domain.models.Task;
import com.bordify.domain.models.dtos.TaskListDTO;
import com.bordify.shared.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Repository interface for accessing and managing task entities in the database.
 */
public interface TaskRepository {

    /**
     * Retrieves a list of task DTOs by topic ID with pagination.
     *
     * @param id The ID of the topic.
     * @param pageable The pagination information.
     * @return A list of task DTOs associated with the specified topic ID.
     */
    public List<TaskListDTO> findByTopicId(UUID id, PageRequest pageable);

    /**
     * Retrieves a task by its ID.
     *
     * @param id The ID of the task.
     * @return An Optional containing the task if found, or empty otherwise.
     */
    public Optional<Task> findById(UUID id);
}