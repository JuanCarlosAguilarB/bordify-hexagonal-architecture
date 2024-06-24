package com.bordify.domain.models;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class TaskItem {

    private UUID    id;
    private String  content;
    private Boolean isDone;
    private Task    task;

}
