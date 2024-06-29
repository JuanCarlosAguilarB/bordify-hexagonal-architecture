package com.bordify.color.infrastructure.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateColorRequest {
    private String name;
    private String hex;
}
