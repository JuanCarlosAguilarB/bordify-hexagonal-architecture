package com.bordify.task.domain;

import com.bordify.topic.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
public class Task {


    private UUID            id;
    private String          name;
    private String          description;
    private Topic topic;

}
