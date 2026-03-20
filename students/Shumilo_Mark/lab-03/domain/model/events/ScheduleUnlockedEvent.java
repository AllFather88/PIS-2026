package org.example.src.domain.model.events;

import java.time.Instant;

public class ScheduleUnlockedEvent implements DomainEvent {

  private final Long courtId;
  private final Instant occurredOn;

  public ScheduleUnlockedEvent(Long courtId) {
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
