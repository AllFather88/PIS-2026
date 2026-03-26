package org.example.src.infrastructure.config.springtest;


import org.example.src.infrastructure.adapter.out.entity.BookingEntity;
import org.example.src.infrastructure.adapter.out.repository.BookingJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class BookingRepositoryTest {

    @Autowired
    BookingJpaRepository repository;

    @Test
    void saveAndLoadBooking() {
        BookingEntity booking = new BookingEntity();
        booking.setCourtId(1L);
        booking.setUserId(10L);
        booking.setStartTime(LocalDateTime.now());
        booking.setEndTime(LocalDateTime.now().plusHours(1));

        BookingEntity saved = repository.save(booking);

        BookingEntity loaded = repository.findById(saved.getId()).orElseThrow();

        assertEquals(saved.getCourtId(), loaded.getCourtId());
        assertEquals(saved.getUserId(), loaded.getUserId());
    }
}

