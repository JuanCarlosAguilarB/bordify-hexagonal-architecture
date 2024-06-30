package com.bordify.auth.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bordify.auth.domain.Auth;
import com.bordify.auth.domain.AuthServices;
import com.bordify.auth.domain.AuthenticationToken;
import com.bordify.users.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class AuthServicesAdapter implements AuthServices {


    private final AuthenticationManager authenticationManager;
    //    @Value("${jwt.secret}")
    private final String secret = "secret";
    private final LocalDate now = LocalDate.now();
    private final Algorithm algorithm = Algorithm.HMAC256(this.secret);


    @Override
    public AuthenticationToken create(User user) {

        String accessToken = getAccessToken(user.getUsername());
        String refreshToken = getRefreshToken(user.getUsername());

        return AuthenticationToken.builder()
                .token(accessToken)
                .refreshToken(refreshToken)
                .build();

    }

    @Override
    public void authenticate(Auth auth) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        auth.getUserName(),
                        auth.getPassword()
                )
        );
    }

    /**
     * Generates a JWT access token with the specified expiration and username.
     *
     * @param expirationInDays The expiration duration of the token in days.
     * @param username The username associated with the token.
     * @return The generated JWT access token.
     */
    private String generateToken(int expirationInDays, String username) {

        LocalDate expiryDate = now.plusDays(expirationInDays);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(java.sql.Timestamp.valueOf(now.atStartOfDay()))
                .withExpiresAt(java.sql.Timestamp.valueOf(expiryDate.atStartOfDay()))
                .sign(algorithm);
    }


    /**
     * Generates an access token with a default expiration period and the specified username.
     *
     * @param username The username associated with the token.
     * @return The generated access token.
     */
    private String getAccessToken(String username) {

        //    @Value("${jwt.accessTokenExpirationInDays:10}")
        int accessTokenExpirationInDays = 1000;
        return generateToken(accessTokenExpirationInDays, username);
    }

    /**
     * Generates a refresh token with a default expiration period and the specified username.
     *
     * @param username The username associated with the token.
     * @return The generated refresh token.
     */
    private String getRefreshToken(String username) {

        //    @Value("${jwt.refreshTokenExpirationInDays:11}")
        int refreshTokenExpirationInDays = 1100;
        return generateToken(refreshTokenExpirationInDays, username);
    }

}
