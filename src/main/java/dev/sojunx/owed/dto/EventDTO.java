package dev.sojunx.owed.dto;

import dev.sojunx.owed.domain.Event;

import java.time.Instant;
import java.util.UUID;

public record EventDTO(UUID id, String title, Instant createdAt, String createdBy) {
    public static EventDTO from(Event event) {
        return new EventDTO(event.getId(), event.getTitle(), event.getCreatedAt(), event.getCreatedBy().getUsername());
    }
}
