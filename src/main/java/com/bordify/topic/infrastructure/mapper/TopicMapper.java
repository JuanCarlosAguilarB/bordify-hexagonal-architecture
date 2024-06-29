package com.bordify.topic.infrastructure.mapper;

import com.bordify.board.domain.Board;
import com.bordify.board.infrastructure.mapper.BoardMapper;
import com.bordify.color.domain.Color;
import com.bordify.color.infrastructure.mapper.ColorMapper;
import com.bordify.topic.domain.Topic;
import com.bordify.topic.infrastructure.persistence.TopicEntity;
import com.bordify.users.infrastructure.persistence.UserEntity;

public class TopicMapper {

    public static TopicEntity toEntity(Topic topic) {
        return TopicEntity.builder()
                .id(topic.getId())
                .name(topic.getName())
                .boardId(topic.getBoard().getId())
                .colorId(topic.getColor().getId())
                .build();
    }


    public static Topic toDomain(TopicEntity topicEntity) {

        BoardMapper boardMapper = new BoardMapper();
        ColorMapper  colorMapper = new ColorMapper();

        Board  board = boardMapper.toDomain(topicEntity.getBoardEntity());
        Color  color = colorMapper.toDomain(topicEntity.getColorEntity());

        return Topic.builder()
                .id(topicEntity.getId())
                .name(topicEntity.getName())
                .board(board)
                .color(color)
                .build();
    }
}
