package com.bordify.topic.infrastructure.controllers;

import com.bordify.task.application.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Tag(name = "TopicEntity", description = "TopicEntity management operations")
public class TopicGetController {

    private final TaskService taskService;

    /**
     * Retrieves tasks associated with a topicEntity.
     *
     * @param topicId  The ID of the topicEntity.
     * @param pageable Pagination information.
     * @return ResponseEntity with tasks of the specified topicEntity.
     */
    @GetMapping("/v1/topic/{topicId}/tasks/")
    public ResponseEntity<?> getTaskOfTopic(@PathVariable UUID topicId, Pageable pageable) {
        return ResponseEntity.ok(taskService.getTaskOfTopic(topicId, pageable));
    }


}
