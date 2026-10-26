package dev.sojunx.owed.response;

import dev.sojunx.owed.domain.Group;

import java.time.Instant;
import java.util.UUID;

public record GroupDTO(UUID id, String title, Instant createdAt) {
    public static GroupDTO from(Group group) {
        return new GroupDTO(group.getId(), group.getTitle(), group.getCreatedAt());
    }
}