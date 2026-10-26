package dev.sojunx.owed.service;

import dev.sojunx.owed.domain.User;
import dev.sojunx.owed.request.SignInRequest;
import dev.sojunx.owed.request.SignUpRequest;
import dev.sojunx.owed.response.AuthResponse;
import dev.sojunx.owed.response.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    public UserDTO signup(SignUpRequest request) {
        if (!request.password().equals(request.confirmPassword()))
            throw new RuntimeException("Passwords don't match");

        User user = new User();
        user.setEmail(request.email());
        user.setHashedPassword(encoder.encode(request.password()));

        return userService.createUser(user);
    }

    public AuthResponse signin(SignInRequest request) {
        User user = userService.findByEmail(request.email());
        if (!encoder.matches(request.password(), user.getHashedPassword()))
            throw new RuntimeException("Invalid credentials");

        String token = jwtService.generate(user.getId());
        return new AuthResponse(token, UserDTO.from(user));
    }
}
