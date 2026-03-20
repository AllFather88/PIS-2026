package org.example.src.application.port.in;

//интерфейс для создания броней

import org.example.src.application.port.DTO.CreateBookingCommand;
import org.example.src.domain.model.aggregates.Booking;


public interface CreateBookingUseCase {
    // создаёт бронь
    Booking create(CreateBookingCommand command);
}
