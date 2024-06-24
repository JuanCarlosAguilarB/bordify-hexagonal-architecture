package com.bordify.domain.ports.out;

import com.bordify.domain.models.User;


/**
 * Repository interface for accessing and managing user entities in the database.
 */
public interface UserRepository {

    /**
     * Checks if a user exists with the given email.
     *
     * @param email The email address of the user.
     * @return True if a user exists with the given email, false otherwise.
     */
    public boolean existsByEmail(String email);

    /**
     * Checks if a user exists with the given username.
     *
     * @param username The username of the user.
     * @return True if a user exists with the given username, false otherwise.
     */
    public boolean existsByUsername(String username);

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user.
     * @return The user with the specified username, or null if not found.
     */
    public User findByUsername(String username);

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user.
     * @return The user with the specified email address, or null if not found.
     */
    public User findByEmail(String email);
}