package dev.sojunx.owed.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups/{groupId}/members")
@RequiredArgsConstructor
public class GroupMemberController {
    @GetMapping
    public void getGroupMembers(@PathVariable UUID groupId) {}

    @PostMapping
    public void addMemberToGroup(@PathVariable UUID groupId) {
        
    }
}
