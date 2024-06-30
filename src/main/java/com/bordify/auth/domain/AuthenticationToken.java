package com.bordify.auth.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationToken {

    private String token;
    private String refreshToken;

}
