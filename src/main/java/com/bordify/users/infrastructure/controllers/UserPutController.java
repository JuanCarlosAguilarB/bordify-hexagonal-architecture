package com.bordify.users.infrastructure.controllers;

import com.bordify.users.application.create.UserCreator;

import com.bordify.users.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Tag(name = "User", description = "User management operations")
@RestController
public class UserPutController {

//    private final PasswordEncoder passwordEncoder;

    private final UserCreator userCreatorServices;

    public UserPutController(
//            PasswordEncoder passwordEncoder,
            UserCreator userCreatorServices
    ) {
//        this.passwordEncoder = passwordEncoder;
        this.userCreatorServices = userCreatorServices;
    }

    @Operation(summary = "Create a new user", description = "Creates a new user", tags = { "User" })
    @PostMapping(value = "/v1/users/")
    public ResponseEntity<?> createUser(@RequestBody RequestUserBody requestBody) {

        User user = User.builder()
                .username(requestBody.getUsername())
                .email(requestBody.getEmail())
                .firstName(requestBody.getFirstName())
                .lastName(requestBody.getLastName())
                .phoneNumber(requestBody.getPhoneNumber())
                .password(requestBody.getPassword())
                .build();

        userCreatorServices.createUser(user);

        Map<String,String> response = Map.of("message","User created");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

}
