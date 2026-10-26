package dev.sojunx.owed.response;

import dev.sojunx.owed.domain.User;

import java.time.Instant;
import java.util.UUID;

public record UserDTO(UUID id, String email, Instant createdAt) {
    public static UserDTO from(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getCreatedAt());
    }
}
