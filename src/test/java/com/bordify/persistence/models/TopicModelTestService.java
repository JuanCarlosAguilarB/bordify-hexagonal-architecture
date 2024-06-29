package com.bordify.persistence.models;

import com.bordify.board.infrastructure.persistence.BoardEntity;
import com.bordify.color.infrastructure.persistence.Color;
import com.bordify.models.Topic;

import java.util.UUID;

public class TopicModelTestService {

    public static Topic createValidTopic(Color color, BoardEntity boardEntity) {

        Topic topic = Topic.builder()
                .id(UUID.randomUUID())
                .name("Test Topic")
                .color(color)
                .colorId(color.getId())
                .boardEntity(boardEntity)
                .boardId(boardEntity.getId())
                .build();

        return topic;
    }

}
