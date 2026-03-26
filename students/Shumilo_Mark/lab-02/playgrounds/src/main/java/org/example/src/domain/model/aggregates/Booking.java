package org.example.src.domain.model.aggregates;

import lombok.Getter;
import org.example.src.application.command.CreateBookingCommand;
import org.example.src.domain.model.events.*;
import org.example.src.domain.model.value_objects.TimeSlot;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// класс доменной сущности "бронь"
@Getter
public class Booking {

  private final Long id;
  private final Long courtId;
  private final Long userId;
  private TimeSlot slot;

  private BookingStatus status = BookingStatus.CREATED;

  private final List<DomainEvent> events = new ArrayList<>();

  public Booking(Long id, Long courtId, Long userId, TimeSlot slot) {
    if (id == null) throw new IllegalArgumentException("Booking ID cannot be null");
    if (courtId == null) throw new IllegalArgumentException("Court ID cannot be null");
    if (userId == null) throw new IllegalArgumentException("User ID cannot be null");
    if (slot == null) throw new IllegalArgumentException("TimeSlot cannot be null");

    this.id = id;
    this.courtId = courtId;
    this.userId = userId;
    this.slot = slot;

    events.add(new BookingCreatedEvent(id, courtId, userId));
  }
  public BookingStatus getStatus(){
    return status;
  }
  public void cancel(String reason) {
    if (status == BookingStatus.COMPLETED)
      throw new IllegalStateException("Cannot cancel completed booking");

    status = BookingStatus.CANCELLED;
    events.add(new BookingCancelledEvent(id, reason));
  }

  public void confirm() {
    if (status != BookingStatus.CREATED)
      throw new IllegalStateException("Only CREATED bookings can be confirmed");

    status = BookingStatus.CONFIRMED;
    events.add(new BookingConfirmedEvent(id));
  }
  public void complete() {
    if (LocalDateTime.now().isBefore(slot.end()))
      throw new IllegalStateException("Cannot complete booking before it ends");

    status = BookingStatus.COMPLETED;
    events.add(new BookingCompletedEvent(id));
  }

  public List<DomainEvent> getEvents() {
    return List.copyOf(events);
  }

  public void clearEvents() {
    events.clear();
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Booking b && Objects.equals(id, b.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public long getId() {
    return id;
  }

  public long getCourtId() {
    return courtId;
  }

  public long getUserId() {
    return userId;
  }


}

