package com.bordify.task_item.domain;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for accessing and managing task item entities in the database.
 */
public interface TaskItemRepository{

    /**
     * Retrieves a list of task items by task ID.
     *
     * @param taskId The ID of the task.
     * @return A list of task items associated with the specified task ID.
     */
    public List<TaskItem> findByTaskId(UUID taskId);

    /**
     * Deletes all task items by IDs not included in the given list.
     *
     * @param ids The list of IDs to keep.
     */
    public void deleteAllByIdNotIn(List<UUID> ids);

    /**
     * Deletes all task items by task ID.
     *
     * @param taskId The ID of the task.
     */
    public void deleteAllByTaskId(UUID taskId);
}
