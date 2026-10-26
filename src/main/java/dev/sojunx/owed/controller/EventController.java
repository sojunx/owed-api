package dev.sojunx.owed.controller;

import dev.sojunx.owed.dto.CreateEventRequest;
import dev.sojunx.owed.dto.CreateParticipantRequest;
import dev.sojunx.owed.dto.EventDTO;
import dev.sojunx.owed.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody CreateEventRequest request, @AuthenticationPrincipal UUID userID) {
        EventDTO res = service.createEvent(userID, request);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    // TODO: Check group is created by current user
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable UUID id) {
        EventDTO res = service.getEventByID(id);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents(@AuthenticationPrincipal UUID userID) {
        List<EventDTO> res = service.getAllEvents(userID);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // TODO: Check group is created by current user
    @PostMapping("/{id}/members")
    public ResponseEntity<?> addMemberToEvent(@PathVariable UUID id, @Valid @RequestBody CreateParticipantRequest request) {
        service.addNewParticipant(id, request.userID());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
