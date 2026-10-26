package dev.sojunx.owed.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantID implements Serializable {
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "event_id")
    private UUID eventId;
}
