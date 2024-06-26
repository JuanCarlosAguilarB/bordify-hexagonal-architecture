package com.bordify.controllers.user;

import com.bordify.models.User;
import com.bordify.infrastructure.ports.out.UserRepository;
import com.bordify.users.application.create.UserService;
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
    private UserService userServices;

    @Autowired
    UserRepository userRepository;

    @Operation(summary = "Get a user", description = "Get a user", tags = { "User" })
    @GetMapping(value = "/users/me/")
    public User getUser() {

        User user = userRepository.findByUsername("1");
        return user;
    }
}
