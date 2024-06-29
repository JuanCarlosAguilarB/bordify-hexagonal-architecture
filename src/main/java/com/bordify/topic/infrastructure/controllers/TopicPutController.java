package com.bordify.topic.infrastructure.controllers;

import com.bordify.board.application.find.BoardFinder;
import com.bordify.board.domain.Board;
import com.bordify.color.application.find.ColorFinder;
import com.bordify.color.domain.Color;
import com.bordify.topic.application.create.TopicCreator;
import com.bordify.topic.domain.Topic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@Tag(name = "TopicEntity", description = "TopicEntity management operations")
@AllArgsConstructor
public class TopicPutController {

    private final TopicCreator topicCreator;
    private final BoardFinder  boardFinder;
    private final ColorFinder  colorFinder;
    /**
     * Update or create a topicEntity.
     *
     * @param id           The ID of the topicEntity to update.
     * @param topicRequest The request body containing information to update the topicEntity.
     * @return ResponseEntity with the updated topicEntity.
     */
    @Operation(summary = "Create a new topicEntity", description = "Creates a new topicEntity", tags = { "TopicEntity" })
    @PutMapping("/v1/topics/{id}/")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody TopicRequest topicRequest) {

        Board board = boardFinder.findBoardById(topicRequest.getBoardId());
        Color color = colorFinder.findColorById(topicRequest.getColorId());

        Topic topic = Topic.builder()
                .id(id)
                .name(topicRequest.getName())
                .color(color)
                .board(board)
                .build();

        topicCreator.create(topic);

        Map<String, Object> response = Map.of(
                "message", "Topic created"
        );
        return ResponseEntity.ok(response);
    }


}
