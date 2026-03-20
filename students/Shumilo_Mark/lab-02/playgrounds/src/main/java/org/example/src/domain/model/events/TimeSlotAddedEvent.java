package org.example.src.domain.model.events;


import java.time.Instant;
import java.time.LocalDateTime;

public class TimeSlotAddedEvent implements DomainEvent {

  private final Long courtId;
  private final LocalDateTime start;
  private final LocalDateTime end;
  private final Instant occurredOn;

  public TimeSlotAddedEvent(Long courtId, LocalDateTime start, LocalDateTime end) {
    this.courtId = courtId;
    this.start = start;
    this.end = end;
    this.occurredOn = Instant.now();
  }

  public Long getCourtId() {
    return courtId;
  }

  public LocalDateTime getStart() {
    return start;
  }

  public LocalDateTime getEnd() {
    return end;
  }

  @Override
  public Instant occurredOn() {
    return occurredOn;
  }
}


