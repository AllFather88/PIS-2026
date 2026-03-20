package org.example.src.domain.model.events;

import java.time.Instant;

public class CourtRenamedEvent implements DomainEvent {

  private final Long courtId;
  private final String newName;
  private final Instant occurredOn;

  public CourtRenamedEvent(Long courtId, String newName) {
    this.courtId = courtId;
    this.newName = newName;
    this.occurredOn = Instant.now();
  }

  public Long getCourtId() {
    return courtId;
  }

  public String getNewName() {
    return newName;
  }

  @Override
  public Instant occurredOn() {
    return occurredOn;
  }
}
