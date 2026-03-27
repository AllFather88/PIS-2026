package org.example.src.domain.model.events;

import java.time.Instant;

public class CourtDeactivatedEvent implements DomainEvent {

  private final Long courtId;
  private final Instant occurredOn;

  public CourtDeactivatedEvent(Long courtId) {
    this.courtId = courtId;
    this.occurredOn = Instant.now();
  }

  public Long getCourtId() {
    return courtId;
  }

  @Override
  public Instant occurredOn() {
    return occurredOn;
  }
}

