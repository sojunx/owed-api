package dev.sojunx.owed.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SignUpRequest(
        @NotNull String username,
        @NotNull @Email String email,
        @NotNull String password,
        @NotNull String confirmPassword
) {}