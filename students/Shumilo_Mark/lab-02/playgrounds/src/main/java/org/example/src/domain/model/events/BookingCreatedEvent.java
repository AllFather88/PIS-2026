package org.example.src.domain.model.events;

import java.time.Instant;

public class BookingCreatedEvent implements DomainEvent {

  private final Long bookingId;
  private final Long courtId;
  private final Long userId;
  private final Instant occurredOn;

  public BookingCreatedEvent(Long bookingId, Long courtId, Long userId) {
    this.bookingId = bookingId;
    this.courtId = courtId;
    this.userId = userId;
    this.occurredOn = Instant.now();
  }

  public Long getBookingId() {
    return bookingId;
  }

  public Long getCourtId() {
    return courtId;
  }

  public Long getUserId() {
    return userId;
  }

  @Override
  public Instant occurredOn() {
    return occurredOn;
  }
}
