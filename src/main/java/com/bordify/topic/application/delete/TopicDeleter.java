package com.bordify.topic.application.delete;

import com.bordify.topic.domain.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TopicDeleter {

    private final TopicRepository topicRepository;

    public void delete(UUID id) {
        topicRepository.deleteById(id);
    }

}
