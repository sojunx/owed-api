package dev.sojunx.owed.repository;

import dev.sojunx.owed.domain.Participant;
import dev.sojunx.owed.domain.ParticipantID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, ParticipantID> {
    List<Participant> findAllByUser_Id(UUID userID);
}