package com.bordify.topic.application.update;

import com.bordify.topic.domain.Topic;
import com.bordify.topic.domain.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TopicUpdater  {

    private final TopicRepository  topicRepository;

    public void update(UUID id, Map<String, Object> topic) {

        Topic topicSearched = topicRepository.findById(id).orElseThrow(() -> new RuntimeException("Topic not found"));

        topic.forEach((key,value) -> {
            Field  field = ReflectionUtils.findField(Topic.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, topicSearched, value);
        });
    }

}
