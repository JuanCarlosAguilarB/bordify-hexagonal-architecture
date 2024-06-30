package com.bordify.users.infrastructure.controllers;

import com.bordify.shared.domain.InvalidRequestArgumentException;
import com.bordify.utils.EmailValidator;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestUserBody {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;

    public void setEmail(String email) {

        EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.isValidEmail(email) || email == null) {
            throw new InvalidRequestArgumentException("Invalid email");
        }
        this.email = email;
    }
}
