package org.example.src.application.port.in;



import org.example.src.application.command.CreateBookingCommand;
import org.example.src.domain.model.aggregates.Booking;
import org.example.src.infrastructure.adapter.out.entity.BookingEntity;

import java.util.List;


//интерфейс для создания броней
public interface CreateBookingUseCase {
    // создаёт бронь
    List<BookingEntity> getActiveBookings(Long id);
    Booking create(CreateBookingCommand command);
}
