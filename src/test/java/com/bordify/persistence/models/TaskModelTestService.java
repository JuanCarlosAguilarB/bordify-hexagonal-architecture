package com.bordify.persistence.models;

import com.bordify.task.infrastructure.persistence.Task;
import com.bordify.topic.infrastructure.persistence.TopicEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskModelTestService {

    public static Task createValidTask(TopicEntity topicEntity) {

//        TopicEntity topicEntity = TopicModelTestService.createValidTopic();

        Task task = Task.builder()
                .id(UUID.randomUUID())
                .name("Test Task")
                .description("This is a test task.")
                .topicEntity(topicEntity)
                .topicId(topicEntity.getId())
                .build();

        return task;
    }


    public static List<Task> createValidListTask(TopicEntity topicEntity, int amountTask) {

        String generatedString = RandomStringUtils.randomAlphanumeric(10);

        List<Task> listTasks = new ArrayList<>();

        for (int i = 0; i < amountTask; i++) {

            String name = RandomStringUtils.randomAlphanumeric(10);
            String description = RandomStringUtils.randomAlphanumeric(10);

            listTasks.add(
                    Task.builder()
                            .id(UUID.randomUUID())
                            .name(name)
                            .description(description)
                            .topicEntity(topicEntity)
                            .topicId(topicEntity.getId())
                            .build()
            );
        }
        return listTasks;
    }
}

