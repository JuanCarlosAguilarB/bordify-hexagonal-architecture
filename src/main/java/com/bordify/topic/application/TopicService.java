package com.bordify.topic.application;

import com.bordify.dtos.TopicListDTO;
import com.bordify.exceptions.EntityNotFound;
import com.bordify.services.TaskService;
import com.bordify.topic.infrastructure.persistence.TopicEntity;
import com.bordify.topic.infrastructure.persistence.TopicRepository;
import com.bordify.utils.UpdateFieldsOfClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * Service class for managing topicEntities within the application.
 * Provides methods to create, update, delete topicEntities and retrieve topicEntities with their associated tasks.
 */
@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TaskService taskService;

    /**
     * Updates an existing topicEntity based on the provided topicEntity object.
     * Ensures the topicEntity exists before updating and returns the updated topicEntity.
     *
     * @param topicEntity The topicEntity with updated fields.
     * @return The updated and saved topicEntity entity.
     */
    public TopicEntity update(TopicEntity topicEntity) {
        ensureTopicExist(topicEntity);

        TopicEntity topicEntityToUpdate = topicRepository.findById(topicEntity.getId()).orElseThrow(() ->
                new EntityNotFound("TopicEntity not found for ID " + topicEntity.getId()));
        UpdateFieldsOfClasses.updateFields(topicEntityToUpdate, topicEntity);
        topicRepository.save(topicEntityToUpdate);

        return TopicEntity.builder()
                .id(topicEntityToUpdate.getId())
                .name(topicEntityToUpdate.getName())
                .colorId(topicEntityToUpdate.getColorId())
                .boardId(topicEntityToUpdate.getBoardId())
                .build();
    }

    /**
     * Verifies if a topicEntity exists in the database based on its ID.
     *
     * @param topicEntity The topicEntity to check.
     */
    public void ensureTopicExist(TopicEntity topicEntity) {
        if (!topicRepository.existsById(topicEntity.getId())) {
            throw new EntityNotFound("TopicEntity not found");
        }
    }

    /**
     * Retrieves a paginated list of topicEntities for a specified board with their related tasks.
     *
     * @param boardId The UUID of the board.
     * @param pageable The pagination information.
     * @return A page of {@link TopicListDTO} each populated with related tasks.
     */
    public Page<TopicListDTO> getTopicsOfBoard(UUID boardId, Pageable pageable) {
        List<TopicListDTO> topics = topicRepository.findByBoardIdCustom(boardId, pageable);
        System.out.println("topicEntities: " + topics);
        int pageNumber = 0;
        int pageSize = 5;
        Pageable pageableTask = PageRequest.of(pageNumber, pageSize);

        for (TopicListDTO topic : topics) {
            topic.setTasks(taskService.getTaskForBoard(topic.getId(), pageableTask));
        }

        return new PageImpl<>(topics, pageable, topics.size());
    }

    /**
     * Deletes a topicEntity by its UUID.
     *
     * @param id The UUID of the topicEntity to be deleted.
     */
    public void deleteTopic(UUID id) {
        topicRepository.deleteById(id);
    }

    /**
     * Saves a new topicEntity entity to the database.
     *
     * @param topicEntity The topicEntity entity to be saved.
     */
    public void createTopic(TopicEntity topicEntity) {
        topicRepository.save(topicEntity);
    }
}