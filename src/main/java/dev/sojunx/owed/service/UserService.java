package dev.sojunx.owed.service;

import dev.sojunx.owed.domain.User;
import dev.sojunx.owed.dto.UserDTO;
import dev.sojunx.owed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    public UserDTO createUser(User user) {
        User saved = repo.save(user);
        return UserDTO.from(saved);
    }

    public UserDTO getUserByID(UUID id) {
        User user = findByID(id);
        return UserDTO.from(user);
    }

    public UserDTO getUserByEmail(String email) {
        User user = findByEmail(email);
        return UserDTO.from(user);
    }

    public User findByID(UUID id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}