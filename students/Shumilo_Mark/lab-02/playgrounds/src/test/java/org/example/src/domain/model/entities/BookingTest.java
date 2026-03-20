package org.example.src.domain.model.entities;

import org.example.src.domain.model.aggregates.Booking;
import org.example.src.domain.model.events.*;
import org.example.src.domain.model.value_objects.TimeSlot;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;


public class BookingTest {

  @Test
  public void shouldCreateBookingAndRegisterEvent() {
    TimeSlot slot = new TimeSlot(
      LocalDateTime.now().minusHours(1),
      LocalDateTime.now().plusHours(1)
    );

    Booking booking = new Booking(1L, 10L, 5L, slot);

    List<DomainEvent> events = booking.getEvents();
    assertEquals(1, events.size());
    assertTrue(events.get(0) instanceof BookingCreatedEvent);
  }

  @Test
  public void shouldConfirmBooking() {
    Booking booking = new Booking(
      1L, 10L, 5L,
      new TimeSlot(LocalDateTime.now(), LocalDateTime.now().plusHours(1))
    );

    booking.confirm();

    assertEquals(BookingStatus.CONFIRMED, booking.getStatus());
    assertTrue(booking.getEvents().stream().anyMatch(e -> e instanceof BookingConfirmedEvent));
  }

  @Test
  public void shouldNotConfirmIfNotCreated() {
    Booking booking = new Booking(
      1L, 10L, 5L,
      new TimeSlot(LocalDateTime.now(), LocalDateTime.now().plusHours(1))
    );

    booking.confirm();

    assertThrows(IllegalStateException.class, booking::confirm);
  }

  @Test
  public void shouldCancelBooking() {
    Booking booking = new Booking(
      1L, 10L, 5L,
      new TimeSlot(LocalDateTime.now(), LocalDateTime.now().plusHours(1))
    );

    booking.cancel("User changed plans");

    assertEquals(BookingStatus.CANCELLED, booking.getStatus());
    assertTrue(booking.getEvents().stream().anyMatch(e -> e instanceof BookingCancelledEvent));
  }

  @Test
  public void shouldNotCancelCompletedBooking() {
    TimeSlot slot = new TimeSlot(
      LocalDateTime.now().minusHours(2),
      LocalDateTime.now().minusHours(1)
    );

    Booking booking = new Booking(1L, 10L, 5L, slot);
    booking.complete();

    assertThrows(IllegalStateException.class, () -> booking.cancel("reason"));
  }

  @Test
  public void shouldCompleteBooking() {
    TimeSlot slot = new TimeSlot(
      LocalDateTime.now().minusHours(2),
      LocalDateTime.now().minusHours(1)
    );

    Booking booking = new Booking(1L, 10L, 5L, slot);
    booking.complete();

    assertEquals(BookingStatus.COMPLETED, booking.getStatus());
    assertTrue(booking.getEvents().stream().anyMatch(e -> e instanceof BookingCompletedEvent));
  }

  @Test
  public void shouldNotCompleteBeforeEndTime() {
    TimeSlot slot = new TimeSlot(
      LocalDateTime.now(),
      LocalDateTime.now().plusHours(1)
    );

    Booking booking = new Booking(1L, 10L, 5L, slot);

    assertThrows(IllegalStateException.class, booking::complete);
  }
}

