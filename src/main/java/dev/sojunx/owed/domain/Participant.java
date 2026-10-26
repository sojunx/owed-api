package dev.sojunx.owed.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "participants")
@NoArgsConstructor
@Setter
@Getter
public class Participant {
    @EmbeddedId
    private ParticipantID id = new ParticipantID();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(nullable = false)
    private String role = "MEMBER";

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant joinedAt;
}
