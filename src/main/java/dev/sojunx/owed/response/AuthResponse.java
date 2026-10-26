package dev.sojunx.owed.response;

public record AuthResponse(String token, UserDTO user) {}
