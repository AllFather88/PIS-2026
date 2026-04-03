package org.example.src.domain.model.events;

import java.time.Instant;

public class BookingCancelledEvent implements DomainEvent {

  private final Long bookingId;
  private final String reason;
  private final Instant occurredOn;

  public BookingCancelledEvent(Long bookingId, String reason) {
    this.bookingId = bookingId;
    this.reason = reason;
    this.occurredOn = Instant.now();
  }

  public Long getBookingId() {
    return bookingId;
  }

  public String getReason() {
    return reason;
  }

  @Override
  public Instant occurredOn() {
    return occurredOn;
  }
}

