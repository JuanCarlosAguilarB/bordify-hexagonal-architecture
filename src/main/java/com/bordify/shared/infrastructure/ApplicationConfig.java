package com.bordify.shared.infrastructure;

import com.bordify.users.infrastructure.ports.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for application-wide configurations.
 * Configures password encoding, user details service, authentication manager, and exception handling.
 */
@Configuration
@RequiredArgsConstructor

public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
