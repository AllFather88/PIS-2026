package org.example.src.infrastructure.config;

import org.example.src.application.command.handler.CreateBookingHandler;
import org.example.src.application.projection.BookingProjection;
import org.example.src.application.projection.ReviewProjection;
import org.example.src.application.service.BookingService;
import org.example.src.infrastructure.adapter.out.bookingRepository;
import org.example.src.infrastructure.adapter.out.repository.BookingReadJpaRepository;
import org.example.src.infrastructure.adapter.out.repository.ReviewReadJpaRepository;
import org.example.src.infrastructure.eventsbus.SimpleEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// класс для описания внедрения зависимостей

@Configuration
public class DependencyInjectionConfig {


    private final bookingRepository bRepository;
    private final SimpleEventBus eventBus;
    private final BookingReadJpaRepository br;
    private final ReviewReadJpaRepository rr;

    public DependencyInjectionConfig(@Autowired bookingRepository bRepository, @Autowired  SimpleEventBus eventBus,@Autowired BookingReadJpaRepository br,@Autowired ReviewReadJpaRepository rr) {
        this.bRepository = bRepository;
        this.br = br;
        this.rr = rr;
        eventBus.register(new BookingProjection(br));
        eventBus.register(new ReviewProjection(rr));
        this.eventBus = eventBus;
    }
    @Bean
    public BookingService getBookingService(){
        CreateBookingHandler handler = new CreateBookingHandler(bRepository,eventBus);
        return new BookingService(handler);
    }
}
