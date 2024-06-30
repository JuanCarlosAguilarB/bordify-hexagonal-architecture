package com.bordify.auth.infrastructure.security;

import java.io.IOException;
import java.time.LocalDate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter class responsible for JWT authentication.
 * Validates JWT tokens from incoming requests and sets the authentication context if valid.
 */
@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final UserDetailsService userDetailsService;

    /**
     * Performs the JWT token validation and sets the authentication context if valid.
     *
     * @param request The HTTP servlet request.
     * @param response The HTTP servlet response.
     * @param filterChain The filter chain for the request.
     * @throws ServletException If an error occurs during the filter execution.
     * @throws IOException If an I/O error occurs during the filter execution.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromRequest(request);
        final String username;

        if (token == null) {
//            ApiResponseHelper.sendErrorResponse(response, HttpStatus.BAD_REQUEST, "Bad Request", "El token no puede ser nulo");
            filterChain.doFilter(request, response);
            return;

        }
        username = getUsernameFromToken(token);

        if (username == null) {
            ApiResponseHelper.sendErrorResponse(response, HttpStatus.BAD_REQUEST, "Bad Request", "El token is not valid");
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (isValidToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                // invalid token
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }

        }

        filterChain.doFilter(request, response);
    }

    /**
     * Extracts the JWT token from the request headers.
     *
     * @param request The HTTP servlet request.
     * @return The JWT token if found, null otherwise.
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

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



