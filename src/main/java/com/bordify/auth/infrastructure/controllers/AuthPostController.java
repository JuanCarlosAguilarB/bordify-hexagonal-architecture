package com.bordify.auth.infrastructure.controllers;

import com.bordify.auth.domain.Auth;
import com.bordify.auth.domain.AuthServices;
import com.bordify.auth.domain.AuthenticationToken;
import com.bordify.users.application.find.UserFinder;
import com.bordify.users.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth", description = "Authentication operations")
@AllArgsConstructor
public class AuthPostController {

    private final UserFinder userFinder;
    private final AuthServices authServices;

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param loginRequest The login request containing the username and password.
     * @return A ResponseEntity with the JWT token and user information.
     */
    @Operation(summary = "Authenticate a user", description = "Authenticates a user and returns a JWT token", tags = { "Auth" })
    @PostMapping("/v1/login/")
    public ResponseEntity<?> authenticateUser(@RequestBody Auth loginRequest) {

        String username = loginRequest.getUserName();

        authServices.authenticate(loginRequest);

        User user = userFinder.findUserByUsername(username);
        AuthenticationToken  authenticationToken = authServices.create(user);

        return ResponseEntity.ok(authenticationToken);
    }

}
