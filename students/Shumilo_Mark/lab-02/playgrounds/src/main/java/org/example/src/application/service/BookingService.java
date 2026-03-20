package org.example.src.application.service;

import org.example.src.application.port.DTO.CreateBookingCommand;
import org.example.src.application.port.in.CreateBookingUseCase;
import org.example.src.domain.model.aggregates.Booking;

public class BookingService implements CreateBookingUseCase {
  @Override
  public Booking create(CreateBookingCommand command) {
    return null;
  }
}
