package org.example.src.application.projection;

import org.example.src.domain.model.events.BookingCreatedEvent;
import org.example.src.infrastructure.adapter.out.entity.BookingReadEntity;
import org.example.src.infrastructure.adapter.out.repository.BookingReadJpaRepository;

public class BookingProjection {

    private final BookingReadJpaRepository repo;

    public BookingProjection(BookingReadJpaRepository repo) {
        this.repo = repo;
    }

    public void on(BookingCreatedEvent e) {
        BookingReadEntity b = new BookingReadEntity();
        b.setId(e.getBookingId());
        b.setUserId(e.getUserId());
        b.setCourtId(e.getCourtId());
        b.setStartTime(e.getStart());
        b.setEndTime(e.getEnd());
        repo.save(b);
    }
}
