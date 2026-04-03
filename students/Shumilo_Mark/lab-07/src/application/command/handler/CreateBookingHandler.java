package org.example.src.application.command.handler;



import org.example.src.application.command.CreateBookingCommand;
import org.example.src.application.eventbus.EventBus;
import org.example.src.application.port.out.BookingRepository;

import org.example.src.domain.model.aggregates.Booking;
import org.example.src.domain.model.value_objects.TimeSlot;

public class CreateBookingHandler {

  private final BookingRepository bookingRepository;
  private final EventBus events;
  public CreateBookingHandler(BookingRepository bookingRepository,EventBus b) {
    this.bookingRepository = bookingRepository;
    this.events = b;
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

