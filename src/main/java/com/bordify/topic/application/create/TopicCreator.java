package com.bordify.topic.application.create;

import com.bordify.topic.domain.Topic;
import com.bordify.topic.domain.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TopicCreator {

    private final TopicRepository topicRepository;

    /**
     * Saves a new topicEntity entity to the database.
     *
     * @param topic The topicEntity entity to be saved.
     */
    public void create(Topic topic) {
        topicRepository.save(topic);
    }

}
