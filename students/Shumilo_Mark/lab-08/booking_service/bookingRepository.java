package org.example.src.infrastructure.adapter.out;

import org.example.src.application.port.out.BookingRepository;
import org.example.src.domain.model.aggregates.Booking;
import org.example.src.infrastructure.adapter.out.mapers.BookingMapper;
import org.example.src.infrastructure.adapter.out.repository.BookingJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// класс реализации обработки исхрдящих запросов для работы с бронями
@Component
public class bookingRepository implements BookingRepository {

  private final BookingJpaRepository jpa;

  public bookingRepository(@Autowired  BookingJpaRepository jpa) {
    this.jpa = jpa;
  }

  @Override
  public Booking save(Booking booking) {
    var entity = BookingMapper.toEntity(booking);
    var saved = jpa.save(entity);
    return BookingMapper.toDomain(saved);
  }

  @Override
  public boolean isSlotAvailable(Long courtId, Booking booking) {
    return !jpa.existsByCourtIdAndStartTimeLessThanAndEndTimeGreaterThan(
      courtId,
      booking.getSlot().end(),
      booking.getSlot().start()
    );
  }
}

