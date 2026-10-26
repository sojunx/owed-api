package dev.sojunx.owed.service;

import dev.sojunx.owed.domain.Group;
import dev.sojunx.owed.domain.User;
import dev.sojunx.owed.repository.GroupRepository;
import dev.sojunx.owed.request.CreateGroupRequest;
import dev.sojunx.owed.response.GroupDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository repo;
    private final UserService userService;

    @Transactional
    public GroupDTO createGroup(CreateGroupRequest request, UUID userId) {

        Group group = new Group();
        group.setTitle(request.title());

        User user = userService.findByID(userId);
        user.joinGroup(group, "OWNER");

        Group saved = repo.saveAndFlush(group);
        return GroupDTO.from(saved);
    }

    public GroupDTO getGroupByID(UUID id) {
        Group group = repo.findById(id).orElseThrow(() -> new RuntimeException("Group not found"));
        return GroupDTO.from(group);
    }
}
