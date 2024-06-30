package com.bordify.auth.infrastructure.controllers;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthJwtResponse {

    private String token;
    private String refreshToken;

}
