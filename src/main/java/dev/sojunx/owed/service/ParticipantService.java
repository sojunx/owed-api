package dev.sojunx.owed.service;

import dev.sojunx.owed.domain.Event;
import dev.sojunx.owed.domain.Participant;
import dev.sojunx.owed.domain.User;
import dev.sojunx.owed.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository repo;

    public void createParticipant(User user, Event event, String role) {
        Participant participant = new Participant();
        participant.setUser(user);
        participant.setEvent(event);
        participant.setRole(role);

        repo.save(participant);
    }
}
