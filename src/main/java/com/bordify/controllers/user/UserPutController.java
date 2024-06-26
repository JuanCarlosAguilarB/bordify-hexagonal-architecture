package com.bordify.controllers.user;

import com.bordify.application.UserService;

import com.bordify.users.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Tag(name = "User", description = "User management operations")
@RestController
public class UserPutController {

    private final PasswordEncoder passwordEncoder;

    private final UserService userServices;

    public UserPutController(PasswordEncoder passwordEncoder, UserService userServices) {
        this.passwordEncoder = passwordEncoder;
        this.userServices = userServices;
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
                .password(passwordEncoder.encode( requestBody.getPassword()))
                .build();

        userServices.createUser(user);

        Map<String,String> response = Map.of("message","User created");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

}
