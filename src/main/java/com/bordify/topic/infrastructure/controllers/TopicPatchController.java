package com.bordify.topic.infrastructure.controllers;

import com.bordify.topic.application.update.TopicUpdater;
import com.bordify.topic.infrastructure.persistence.TopicEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

/**
 * Controller class for managing topicEntities.
 */
@RestController
@Tag(name = "TopicEntity", description = "TopicEntity management operations")
@AllArgsConstructor
public class TopicPatchController {

    private final TopicUpdater topicUpdater;
    /**
     * Partially updates a topicEntity.
     *
     * @param id            The ID of the topicEntity to update.
     * @param topicRequest  The request body containing partial information to update the topicEntity.
     * @return ResponseEntity with the updated topicEntity.
     */
    @PatchMapping("/v1/topics/{id}/")
    public ResponseEntity<?> partialUpdate(
        @PathVariable UUID id,
         @RequestBody Map<String, Object> topicRequest) {

        topicUpdater.update(id, topicRequest);

        Map<String, String> response = Map.of("message", "topic updated");
        return ResponseEntity.ok(response);
    }



}
