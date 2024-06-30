package com.bordify.task_item.domain;

import com.bordify.task.domain.Task;
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
    private Task task;

}
