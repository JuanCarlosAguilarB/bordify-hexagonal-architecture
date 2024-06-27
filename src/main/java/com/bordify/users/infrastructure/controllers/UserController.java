package com.bordify.users.infrastructure.controllers;

import com.bordify.users.infrastructure.persistence.UserEntity;
import com.bordify.users.infrastructure.ports.out.UserRepository;
import com.bordify.users.application.create.UserCreator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;




@Tag(name = "User", description = "User management operations")
@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCreator userCreatorServices;

    @Autowired
    UserRepository userRepository;

    @Operation(summary = "Get a user", description = "Get a user", tags = { "User" })
    @GetMapping(value = "/users/me/")
    public UserEntity getUser() {

        UserEntity user = userRepository.findByUsername("1");
        return user;
    }
}
