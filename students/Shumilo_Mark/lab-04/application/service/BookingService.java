package org.example.src.application.service;


import org.example.src.application.command.CreateBookingCommand;
import org.example.src.application.command.handler.CreateBookingHandler;
import org.example.src.application.port.in.CreateBookingUseCase;
import org.example.src.domain.model.aggregates.Booking;

public class BookingService implements CreateBookingUseCase {

  private final CreateBookingHandler handler;
  public BookingService(CreateBookingHandler handler){
      this.handler = handler;
  }
  @Override
  public Booking create(CreateBookingCommand command) {
    return handler.handle(command);
  }
}
