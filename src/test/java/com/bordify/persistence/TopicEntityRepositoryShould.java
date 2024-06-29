package com.bordify.persistence;

import com.bordify.board.infrastructure.persistence.BoardEntity;
import com.bordify.board.infrastructure.persistence.BoardJpaRepository;
import com.bordify.color.infrastructure.persistence.ColorEntity;
import com.bordify.topic.domain.TopicListDTO;
import com.bordify.topic.infrastructure.persistence.TopicEntity;
import com.bordify.boards.infrastructure.persistence.models.BoardModelTestService;
import com.bordify.persistence.models.ColorModelTestService;
import com.bordify.persistence.models.TopicModelTestService;
import com.bordify.color.infrastructure.persistence.ColorJpaRepository;
import com.bordify.topic.infrastructure.persistence.TopicJpaRepository;
import com.bordify.users.domain.UserModelTestService;
import com.bordify.users.infrastructure.persistence.UserEntity;
import com.bordify.users.infrastructure.persistence.UserJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@DataJpaTest
public class TopicEntityRepositoryShould {

    @Autowired
    private TopicJpaRepository topicJpaRepository;

    @Autowired
    private BoardJpaRepository boardRepository;

    @Autowired
    private ColorJpaRepository colorJpaRepository;

    @Autowired
    private UserJpaRepository userRepository;


    @DisplayName("Should find one topicEntity by boardId")
    @Test
    public void shouldFindOneTopicByBoardId() {

        UserEntity userTest = UserModelTestService.createValidUserEntity();
        userRepository.save(userTest);

        BoardEntity boardTest = BoardModelTestService.createValidBoard(userTest);
        BoardEntity  noRalatedBoardTest = BoardModelTestService.createValidBoard(userTest);
        boardRepository.saveAll(List.of(boardTest,noRalatedBoardTest));

        ColorEntity colorEntityTest = ColorModelTestService.createValidColor();
        colorJpaRepository.save(colorEntityTest);

        TopicEntity topicEntityTest = TopicModelTestService.createValidTopic(colorEntityTest, boardTest);
        TopicEntity noRalatedTopicEntityTest = TopicModelTestService.createValidTopic(colorEntityTest, noRalatedBoardTest);
        topicJpaRepository.saveAll(List.of(topicEntityTest, noRalatedTopicEntityTest));

        Pageable pageable = Pageable.unpaged();
        List<TopicListDTO> topicFromDb = topicJpaRepository.findByBoardIdCustom(topicEntityTest.getBoardId(), pageable);

        // assert for verify that are not related topicEntities
        Assertions.assertNotEquals(topicEntityTest.getBoardId(), noRalatedTopicEntityTest.getBoardId());

        Assertions.assertEquals(1, topicFromDb.size());

        Assertions.assertEquals(topicEntityTest.getId(), topicFromDb.get(0).getId());

    }


}
