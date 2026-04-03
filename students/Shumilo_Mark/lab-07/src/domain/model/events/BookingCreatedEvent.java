package org.example.src.domain.model.events;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class BookingCreatedEvent implements DomainEvent {

  private final Long bookingId;
  private final Long courtId;
  private final Long userId;
  private final LocalDateTime start;
  private final LocalDateTime end;

  private final Instant occurredOn;

  public BookingCreatedEvent(Long id, Long courtId, Long userId, LocalDateTime start, LocalDateTime end) {
    this.bookingId = id;
    this.courtId = courtId;
    this.userId = userId;
    this.start = start;
    this.end = end;
    this.occurredOn = Instant.now();
  }





  @Override
  public Instant occurredOn() {
    return occurredOn;
  }
}
