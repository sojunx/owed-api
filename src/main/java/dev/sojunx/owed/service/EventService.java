package dev.sojunx.owed.service;

import dev.sojunx.owed.domain.Event;
import dev.sojunx.owed.domain.User;
import dev.sojunx.owed.dto.CreateEventRequest;
import dev.sojunx.owed.dto.EventDTO;
import dev.sojunx.owed.repository.EventRepository;
import dev.sojunx.owed.repository.ParticipantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository repo;
    private final UserService userService;
    private final ParticipantService participantService;
    private final ParticipantRepository participantRepo;

    @Transactional
    public EventDTO createEvent(UUID userID, CreateEventRequest request) {
        User user = userService.findByID(userID);

        Event event = new Event();
        event.setTitle(request.title());
        event.setCreatedBy(user);

        Event saved = repo.saveAndFlush(event);
        participantService.createParticipant(user, event, "ADMIN");

        return EventDTO.from(saved);
    }

    public EventDTO getEventByID(UUID id) {
        Event event = findByID(id);

        return EventDTO.from(event);
    }

    public List<EventDTO> getAllEvents(UUID userID) {
        var participants = participantRepo.findAllByUser_Id(userID);

        return participants.stream().map(participant -> EventDTO.from(participant.getEvent())).toList();
    }

    public void addNewParticipant(UUID id, UUID userID) {
        Event event = findByID(id);
        participantService.createParticipant(userService.findByID(userID), event, "MEMBER");
    }

    private Event findByID(UUID id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }
}
