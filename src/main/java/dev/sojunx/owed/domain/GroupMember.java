package dev.sojunx.owed.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "group_members")
@NoArgsConstructor
@Getter
@Setter
public class GroupMember {
    @EmbeddedId
    private GroupMemberId id = new GroupMemberId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-member")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    @JsonBackReference(value = "group-member")
    private Group group;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant joinedAt;

    public GroupMember(User user, Group group, String role) {
        this.user = user;
        this.group = group;
        this.role = role;
        this.id = new GroupMemberId(user.getId(), group.getId());
    }
}
