package org.example.src.domain.model.events;

import java.time.Instant;

public class BookingCompletedEvent implements DomainEvent {

  private final Long bookingId;
  private final Instant occurredOn;

  public BookingCompletedEvent(Long bookingId) {
    this.bookingId = bookingId;
    this.occurredOn = Instant.now();
  }

  public Long getBookingId() {
    return bookingId;
  }

  @Override
  public Instant occurredOn() {
    return occurredOn;
  }
}
