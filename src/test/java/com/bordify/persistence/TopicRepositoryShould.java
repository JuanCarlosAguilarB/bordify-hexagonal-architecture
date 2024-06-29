package com.bordify.persistence;

import com.bordify.board.infrastructure.persistence.BoardEntity;
import com.bordify.board.infrastructure.persistence.BoardJpaRepository;
import com.bordify.color.infrastructure.persistence.ColorEntity;
import com.bordify.dtos.TopicListDTO;
import com.bordify.models.Topic;
import com.bordify.boards.infrastructure.persistence.models.BoardModelTestService;
import com.bordify.persistence.models.ColorModelTestService;
import com.bordify.persistence.models.TopicModelTestService;
import com.bordify.color.infrastructure.persistence.ColorJpaRepository;
import com.bordify.repositories.TopicRepository;
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
public class TopicRepositoryShould {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private BoardJpaRepository boardRepository;

    @Autowired
    private ColorJpaRepository colorJpaRepository;

    @Autowired
    private UserJpaRepository userRepository;


    @DisplayName("Should find one topic by boardId")
    @Test
    public void shouldFindOneTopicByBoardId() {

        UserEntity userTest = UserModelTestService.createValidUserEntity();
        userRepository.save(userTest);

        BoardEntity boardTest = BoardModelTestService.createValidBoard(userTest);
        BoardEntity  noRalatedBoardTest = BoardModelTestService.createValidBoard(userTest);
        boardRepository.saveAll(List.of(boardTest,noRalatedBoardTest));

        ColorEntity colorEntityTest = ColorModelTestService.createValidColor();
        colorJpaRepository.save(colorEntityTest);

        Topic topicTest = TopicModelTestService.createValidTopic(colorEntityTest, boardTest);
        Topic noRalatedTopicTest = TopicModelTestService.createValidTopic(colorEntityTest, noRalatedBoardTest);
        topicRepository.saveAll(List.of(topicTest, noRalatedTopicTest));

        Pageable pageable = Pageable.unpaged();
        List<TopicListDTO> topicFromDb = topicRepository.findByBoardIdCustom(topicTest.getBoardId(), pageable);

        // assert for verify that are not related topics
        Assertions.assertNotEquals(topicTest.getBoardId(), noRalatedTopicTest.getBoardId());

        Assertions.assertEquals(1, topicFromDb.size());

        Assertions.assertEquals(topicTest.getId(), topicFromDb.get(0).getId());

    }


}
