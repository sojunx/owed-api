package dev.sojunx.owed.controller;

import dev.sojunx.owed.dto.AuthResponse;
import dev.sojunx.owed.dto.SignInRequest;
import dev.sojunx.owed.dto.SignUpRequest;
import dev.sojunx.owed.dto.UserDTO;
import dev.sojunx.owed.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody SignUpRequest request) {
        UserDTO user = service.signup(request);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@Valid @RequestBody SignInRequest request) {
        AuthResponse res = service.signin(request);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}