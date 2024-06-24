package com.bordify.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
public class Task {


    private UUID            id;
    private String          name;
    private String          description;
    private Topic           topic;

}
