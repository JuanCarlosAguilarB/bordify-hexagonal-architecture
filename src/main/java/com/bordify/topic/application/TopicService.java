package com.bordify.topic.application;

import com.bordify.dtos.TopicListDTO;
import com.bordify.exceptions.EntityNotFound;
import com.bordify.services.TaskService;
import com.bordify.topic.infrastructure.persistence.TopicEntity;
import com.bordify.topic.infrastructure.persistence.TopicJpaRepository;
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
    private TopicJpaRepository topicJpaRepository;

    @Autowired
    private TaskService taskService;

    /**
     * Retrieves a paginated list of topicEntities for a specified board with their related tasks.
     *
     * @param boardId The UUID of the board.
     * @param pageable The pagination information.
     * @return A page of {@link TopicListDTO} each populated with related tasks.
     */
    public Page<TopicListDTO> getTopicsOfBoard(UUID boardId, Pageable pageable) {
        List<TopicListDTO> topics = topicJpaRepository.findByBoardIdCustom(boardId, pageable);
        System.out.println("topicEntities: " + topics);
        int pageNumber = 0;
        int pageSize = 5;
        Pageable pageableTask = PageRequest.of(pageNumber, pageSize);

        for (TopicListDTO topic : topics) {
            topic.setTasks(taskService.getTaskForBoard(topic.getId(), pageableTask));
        }

        return new PageImpl<>(topics, pageable, topics.size());
    }

}