package dev.sojunx.owed.controller;

import dev.sojunx.owed.request.CreateGroupRequest;
import dev.sojunx.owed.response.GroupDTO;
import dev.sojunx.owed.response.UserDTO;
import dev.sojunx.owed.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService service;

    @GetMapping
    public void getGroups() {}

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroup(@PathVariable UUID id) {
        GroupDTO dto = service.getGroupByID(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(@Valid @RequestBody CreateGroupRequest request, @AuthenticationPrincipal UserDTO user) {
        GroupDTO dto = service.createGroup(request, user.id());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
