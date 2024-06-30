package com.bordify.auth.application;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Service class for JWT token generation and validation.
 */
@Service
public class JwtService {

//    @Value("${jwt.secret}")
    private final String secret = "secret";
    private final LocalDate now = LocalDate.now();
    final Algorithm algorithm = Algorithm.HMAC256(this.secret);


    /**
     * Extracts the username from the provided JWT token.
     *
     * @param token The JWT token from which to extract the username.
     * @return The username extracted from the token.
     */
    public String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }

    /**
     * Validates whether the provided JWT token is valid for the given user details.
     *
     * @param token The JWT token to validate.
     * @param userDetails The user details against which to validate the token.
     * @return True if the token is valid for the given user details, false otherwise.
     */
    public Boolean isValidToken(String token, UserDetails userDetails) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            LocalDate now = LocalDate.now();

            return !decodedJWT.getExpiresAt().before(java.sql.Date.valueOf(now)) ||
                    decodedJWT.getSubject().equals(userDetails.getUsername());

        } catch (JWTDecodeException exception) {
            return false;
        }
    }

}

