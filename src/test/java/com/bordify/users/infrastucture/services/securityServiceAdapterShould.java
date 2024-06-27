package com.bordify.users.infrastucture.services;



import com.bordify.users.domain.SecurityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class securityServiceAdapterShould {


    @Autowired
    private SecurityService securityService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode() {
        String textToEncode = "password123";

        String encodedText = securityService.encode(textToEncode);

        assertTrue(passwordEncoder.matches(textToEncode, encodedText));
    }

}
