package com.bordify.auth.domain;


/**
 * Exception thrown for JWT token-related errors.
 */
public class TokenException extends RuntimeException {

    /**
     * Constructs a new TokenException with the specified error message.
     *
     * @param message The detail message of the exception.
     */
    public TokenException(String message) {
        super(message);
    }
}
