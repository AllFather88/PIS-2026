package org.example.src.domain.model.events;

import java.time.Instant;

public interface DomainEvent {
  Instant occurredOn();
}
