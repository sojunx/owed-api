package dev.sojunx.owed.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateParticipantRequest(@NotNull UUID userID) {}
