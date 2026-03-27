package org.example.src.infrastructure.config;

import org.example.src.application.command.handler.CreateBookingHandler;
import org.example.src.application.command.handler.CreateReviewHandler;
import org.example.src.application.port.in.SearchCourtsUseCase;
import org.example.src.application.port.out.CourtRepository;
import org.example.src.application.projection.BookingProjection;
import org.example.src.application.projection.ReviewProjection;
import org.example.src.application.query.handler.GetCourtScheduleHandler;
import org.example.src.application.query.handler.SearchCourtsHandler;
import org.example.src.application.service.BookingService;
import org.example.src.application.service.CourtService;
import org.example.src.application.service.ReviewService;
import org.example.src.application.service.ScheduleService;
import org.example.src.domain.model.aggregates.Review;
import org.example.src.infrastructure.adapter.in.CourtController;
import org.example.src.infrastructure.adapter.out.bookingRepository;
import org.example.src.infrastructure.adapter.out.courtRepository;
import org.example.src.infrastructure.adapter.out.repository.BookingReadJpaRepository;
import org.example.src.infrastructure.adapter.out.repository.ReviewReadJpaRepository;
import org.example.src.infrastructure.adapter.out.reviewRepository;
import org.example.src.infrastructure.adapter.out.scheduleRepository;
import org.example.src.infrastructure.eventsbus.SimpleEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// класс для описания внедрения зависимостей

@Configuration
public class DependencyInjectionConfig {


    private final reviewRepository rRepository;

    private final SimpleEventBus eventBus;
    private final BookingReadJpaRepository br;
    private final ReviewReadJpaRepository rr;

    public DependencyInjectionConfig( @Autowired courtRepository cRepository, @Autowired reviewRepository rRepository,  @Autowired  SimpleEventBus eventBus,@Autowired BookingReadJpaRepository br,@Autowired ReviewReadJpaRepository rr) {
        this.rRepository = rRepository;
        this.br = br;
        this.rr = rr;
        eventBus.register(new BookingProjection(br));
        eventBus.register(new ReviewProjection(rr));
        this.eventBus = eventBus;
    }

    @Bean
    public ReviewService getReviewService(){
        CreateReviewHandler handler = new CreateReviewHandler(rRepository,eventBus);
        return new ReviewService(handler);
    }

}
