package com.bordify.auth.infrastructure.controllers;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;

}
