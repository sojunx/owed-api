package dev.sojunx.owed.dto;

import dev.sojunx.owed.domain.User;

import java.time.Instant;
import java.util.UUID;

public record UserDTO(UUID id, String username, String email, Instant createdAt) {
    public static UserDTO from(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt());
    }
}