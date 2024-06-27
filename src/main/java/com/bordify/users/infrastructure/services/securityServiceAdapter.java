package com.bordify.users.infrastructure.services;

import com.bordify.users.domain.SecurityService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class securityServiceAdapter implements SecurityService {

    private final PasswordEncoder passwordEncoder;

    public securityServiceAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String textToEncode) {
        return passwordEncoder.encode(textToEncode);
    }
}
