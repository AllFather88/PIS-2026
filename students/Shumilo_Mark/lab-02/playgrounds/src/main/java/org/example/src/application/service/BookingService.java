package org.example.src.application.service;


import org.example.src.application.command.CreateBookingCommand;
import org.example.src.application.command.handler.CreateOrGetBookingHandler;
import org.example.src.application.port.in.CreateBookingUseCase;
import org.example.src.domain.model.aggregates.Booking;
import org.example.src.infrastructure.adapter.out.entity.BookingEntity;

import java.util.List;

public class BookingService implements CreateBookingUseCase {

  private final CreateOrGetBookingHandler handler;
  public BookingService(CreateOrGetBookingHandler handler){
      this.handler = handler;
  }

  @Override
  public List<BookingEntity> getActiveBookings(Long id) {
    return handler.getActiveBookings(id);
  }

  @Override
  public Booking create(CreateBookingCommand command) {
    return handler.handle(command);
  }
}
