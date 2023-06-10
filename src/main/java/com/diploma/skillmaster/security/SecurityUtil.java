package com.diploma.skillmaster.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Utility interface for retrieving the currently authenticated user.
 */
public interface SecurityUtil {

    /**
     * Retrieves the username of the currently authenticated user.
     *
     * @return An Optional containing the username if a user is authenticated, or an empty Optional otherwise.
     */
    static Optional<String> getSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            return Optional.of(currentUsername);
        }
        return Optional.empty();
    }
}
