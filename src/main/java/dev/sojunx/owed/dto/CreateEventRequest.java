package dev.sojunx.owed.dto;

import jakarta.validation.constraints.NotNull;

public record CreateEventRequest(@NotNull String title) {}
