package dev.sojunx.owed.repository;

import dev.sojunx.owed.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {}