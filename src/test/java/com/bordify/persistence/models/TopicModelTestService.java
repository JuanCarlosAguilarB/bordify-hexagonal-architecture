package com.bordify.persistence.models;

import com.bordify.board.infrastructure.persistence.BoardEntity;
import com.bordify.color.infrastructure.persistence.ColorEntity;
import com.bordify.topic.infrastructure.persistence.TopicEntity;

import java.util.UUID;

public class TopicModelTestService {

    public static TopicEntity createValidTopic(ColorEntity colorEntity, BoardEntity boardEntity) {

        TopicEntity topicEntity = TopicEntity.builder()
                .id(UUID.randomUUID())
                .name("Test TopicEntity")
                .colorEntity(colorEntity)
                .colorId(colorEntity.getId())
                .boardEntity(boardEntity)
                .boardId(boardEntity.getId())
                .build();

        return topicEntity;
    }

}
