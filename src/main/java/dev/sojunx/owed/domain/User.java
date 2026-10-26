package dev.sojunx.owed.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String hashedPassword;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-member")
    private Set<GroupMember> groupMemberships = new HashSet<>();

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    public void joinGroup(Group group, String role) {
        GroupMember membership = new GroupMember(this, group, role);

        this.groupMemberships.add(membership);
        group.getMembers().add(membership);
    }

    public void leaveGroup(Group group) {
        GroupMember membership = new GroupMember(this, group, null);

        group.getMembers().remove(membership);
        this.groupMemberships.remove(membership);
    }
}
