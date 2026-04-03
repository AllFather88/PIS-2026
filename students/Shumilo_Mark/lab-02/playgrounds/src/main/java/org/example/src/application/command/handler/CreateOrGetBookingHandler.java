package org.example.src.application.command.handler;



import org.example.src.application.command.CreateBookingCommand;
import org.example.src.application.eventbus.EventBus;
import org.example.src.application.port.out.BookingRepository;

import org.example.src.domain.model.aggregates.Booking;
import org.example.src.domain.model.value_objects.TimeSlot;
import org.example.src.infrastructure.adapter.out.entity.BookingEntity;

import java.util.List;

public class CreateOrGetBookingHandler {

  private final BookingRepository bookingRepository;
  private final EventBus events;
  public CreateOrGetBookingHandler(BookingRepository bookingRepository, EventBus b) {
    this.bookingRepository = bookingRepository;
    this.events = b;
  }
  public List<BookingEntity> getActiveBookings(Long id) {
    return bookingRepository.getActiveBookings(id);
  }
  public Booking handle(CreateBookingCommand command) {

    TimeSlot slot = new TimeSlot(
      command.getStart(),
      command.getEnd()
    );

    Booking booking = new Booking(
      1l,
      command.getCourtId(),
      command.getUserId(),
      slot
    );
    events.publishAll(booking.getEvents());
    return bookingRepository.save(booking);
  }
}

