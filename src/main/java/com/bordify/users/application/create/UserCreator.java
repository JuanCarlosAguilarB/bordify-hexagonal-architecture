package com.bordify.users.application.create;

import com.bordify.users.domain.User;
import com.bordify.users.domain.UserRepository;
import com.bordify.users.domain.DuplicateEmailException;
import com.bordify.users.domain.UserNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Service class for user-related operations.
 */
@Service
public class UserCreator {

    private final UserRepository userRepository;

    public UserCreator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Creates a new user with the provided user details and generates an authentication token.
     *
     * @param user The user object containing the user details to be created.
     * @throws DuplicateEmailException If the provided email or username is already registered.
     */
    public void createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail()) && userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateEmailException("Both the email " + user.getEmail() + " and username " + user.getUsername() + " are already registered. Please use different credentials.");
        } else if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateEmailException("The email " + user.getEmail() + " is already registered. Please use a different email.");
        } else if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateEmailException("The username " + user.getUsername() + " is already registered. Please use a different username.");
        }

        userRepository.save(user);

    }

}
