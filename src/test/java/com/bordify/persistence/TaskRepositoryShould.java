package com.bordify.persistence;


import com.bordify.board.infrastructure.persistence.BoardEntity;
import com.bordify.board.infrastructure.persistence.BoardJpaRepository;
import com.bordify.boards.infrastructure.persistence.models.BoardModelTestService;
import com.bordify.color.infrastructure.persistence.ColorEntity;
import com.bordify.color.infrastructure.persistence.ColorJpaRepository;
import com.bordify.persistence.models.*;
import com.bordify.task.infrastructure.persistence.Task;
import com.bordify.task.infrastructure.persistence.TaskRepository;
import com.bordify.topic.infrastructure.persistence.TopicEntity;
import com.bordify.topic.infrastructure.persistence.TopicJpaRepository;
import com.bordify.users.infrastructure.persistence.UserEntity;
import com.bordify.users.infrastructure.persistence.UserJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.bordify.users.domain.UserModelTestService.createValidUserEntity;

@DataJpaTest
public class TaskRepositoryShould {


    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserJpaRepository userRepository;
    @Autowired
    private BoardJpaRepository boardRepository;
    @Autowired
    private ColorJpaRepository colorJpaRepository;
    @Autowired
    private TopicJpaRepository topicJpaRepository;

    @DisplayName("Should find task by id")
    @Test
    public void shouldFindTaskById() {

        UserEntity userTest = createValidUserEntity();
        userRepository.save(userTest);

        BoardEntity boardEntityTest = BoardModelTestService.createValidBoard(userTest);
        boardRepository.save(boardEntityTest);

        ColorEntity colorEntityTest = ColorModelTestService.createValidColor();
        colorJpaRepository.save(colorEntityTest);

        TopicEntity topicEntityTest = TopicModelTestService.createValidTopic(colorEntityTest, boardEntityTest);
        topicJpaRepository.save(topicEntityTest);


        Task taskTest = TaskModelTestService.createValidTask(topicEntityTest);
        taskRepository.save(taskTest);

        Optional<Task> tasks = taskRepository.findById(taskTest.getId());

        assert tasks.isPresent();
        assert tasks.get().getId().equals(taskTest.getId());

    }

    @DisplayName("Should find all tasks of topicEntity")
    @Test
    public void shouldFindAllTasksOfTopic() {

        UserEntity userTest = createValidUserEntity();
        userRepository.save(userTest);

        BoardEntity boardEntityTest = BoardModelTestService.createValidBoard(userTest);
        boardRepository.save(boardEntityTest);

        ColorEntity colorEntityTest = ColorModelTestService.createValidColor();
        colorJpaRepository.save(colorEntityTest);

        TopicEntity topicEntityTest = TopicModelTestService.createValidTopic(colorEntityTest, boardEntityTest);
        topicJpaRepository.save(topicEntityTest);

        List<Task> listTaskTopic =  TaskModelTestService.createValidListTask(topicEntityTest, 5);
        taskRepository.saveAll(listTaskTopic);

        Pageable pageable = Pageable.unpaged();
        List<TaskListDTO> tasks = taskRepository.findByTopicId(topicEntityTest.getId(), pageable);

        assert !tasks.isEmpty();
        assert tasks.size() == 5;
        assert tasks.stream().allMatch(task -> task.getTopicId().equals(topicEntityTest.getId()));


    }



}
