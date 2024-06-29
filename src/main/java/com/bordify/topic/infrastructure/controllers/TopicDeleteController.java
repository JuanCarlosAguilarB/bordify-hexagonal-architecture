package com.bordify.topic.infrastructure.controllers;

import com.bordify.topic.application.delete.TopicDeleter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Tag(name = "TopicEntity", description = "TopicEntity management operations")
@AllArgsConstructor
public class TopicDeleteController {


    private final TopicDeleter  topicService;

    /**
     * Deletes a topicEntity by its ID.
     *
     * @param id The ID of the topicEntity to delete.
     */
    @DeleteMapping("/v1/topics/{id}/")
    public void deleteTopic(@PathVariable UUID id) {
        topicService.delete(id);
    }


}
