package org.example.src.application.port.in;



import org.example.src.application.command.CreateBookingCommand;
import org.example.src.domain.model.aggregates.Booking;


//интерфейс для создания броней
public interface CreateBookingUseCase {
    // создаёт бронь
    Booking create(CreateBookingCommand command);
}
