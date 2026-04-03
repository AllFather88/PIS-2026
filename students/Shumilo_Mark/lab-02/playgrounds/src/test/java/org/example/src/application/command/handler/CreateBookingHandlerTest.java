package org.example.src.application.command.handler;



import org.example.src.application.command.CreateBookingCommand;
import org.example.src.application.eventbus.EventBus;
import org.example.src.application.port.out.BookingRepository;
import org.example.src.domain.model.aggregates.Booking;
import org.example.src.domain.model.value_objects.TimeSlot;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreateBookingHandlerTest {

  @Test
  public void testCreateBooking() {
    BookingRepository repo = mock(BookingRepository.class);
    EventBus b = mock(EventBus.class);
    CreateOrGetBookingHandler handler = new CreateOrGetBookingHandler(repo,b);

    LocalDateTime start = LocalDateTime.now();
    LocalDateTime end = start.plusHours(1);

    CreateBookingCommand command = new CreateBookingCommand(
      10L, 20L, start, end
    );

    Booking saved = new Booking(1L, 10L, 20L, new TimeSlot(start, end));

    when(repo.save(any(Booking.class))).thenReturn(saved);

    Booking result = handler.handle(command);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals(10L, result.getCourtId());
    assertEquals(20L, result.getUserId());

    verify(repo, times(1)).save(any(Booking.class));
  }
}
