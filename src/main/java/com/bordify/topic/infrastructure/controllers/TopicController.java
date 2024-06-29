package com.bordify.topic.infrastructure.controllers;

import com.bordify.services.TaskService;
import com.bordify.topic.application.TopicService;
import com.bordify.topic.infrastructure.persistence.TopicEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller class for managing topicEntities.
 */
@RestController
@Tag(name = "TopicEntity", description = "TopicEntity management operations")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TaskService taskService;

    /**
     * Retrieves tasks associated with a topicEntity.
     *
     * @param topicId  The ID of the topicEntity.
     * @param pageable Pagination information.
     * @return ResponseEntity with tasks of the specified topicEntity.
     */
    @GetMapping("/topic/{topicId}/tasks/")
    public ResponseEntity<?> getTaskOfTopic(@PathVariable UUID topicId, Pageable pageable) {
        return ResponseEntity.ok(taskService.getTaskOfTopic(topicId, pageable));
    }


    /**
     * Partially updates a topicEntity.
     *
     * @param id            The ID of the topicEntity to update.
     * @param topicRequest  The request body containing partial information to update the topicEntity.
     * @return ResponseEntity with the updated topicEntity.
     */
    @PatchMapping("/topics/{id}/")
    public ResponseEntity<?> partialUpdate(
        @PathVariable UUID id,
         @RequestBody TopicRequest topicRequest) {

        TopicEntity topicEntity = new TopicEntity().builder()
                .id(id)
                .name(topicRequest.getName())
                .colorId(topicRequest.getColorId())
                .boardId(topicRequest.getBoardId())
                .build();

//        TopicEntity topicEntityUpdated = topicService.update(topicEntity);

        return ResponseEntity.ok("a");
    }



}
