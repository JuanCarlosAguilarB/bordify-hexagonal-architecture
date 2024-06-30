package com.bordify.auth.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Auth {

    private String userName;
    private String password;

}
