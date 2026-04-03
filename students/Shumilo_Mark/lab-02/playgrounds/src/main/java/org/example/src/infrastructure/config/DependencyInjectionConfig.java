package org.example.src.infrastructure.config;


import org.example.src.application.command.handler.CreateOrGetBookingHandler;
import org.example.src.application.command.handler.CreateReviewHandler;
import org.example.src.application.query.handler.GetCourtScheduleHandler;
import org.example.src.application.query.handler.SearchCourtsHandler;
import org.example.src.application.service.BookingService;
import org.example.src.application.service.CourtService;
import org.example.src.application.service.ReviewService;
import org.example.src.application.service.ScheduleService;
import org.example.src.infrastructure.adapter.out.bookingRepository;
import org.example.src.infrastructure.adapter.out.courtRepository;
import org.example.src.infrastructure.adapter.out.repository.BookingReadJpaRepository;
import org.example.src.infrastructure.adapter.out.repository.ReviewReadJpaRepository;
import org.example.src.infrastructure.adapter.out.reviewRepository;
import org.example.src.infrastructure.adapter.out.scheduleRepository;
import org.example.src.infrastructure.eventsbus.RabbitMqEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// класс для описания внедрения зависимостей

@Configuration
public class DependencyInjectionConfig {


    private final bookingRepository bRepository;
    private final courtRepository cRepository;
    private final reviewRepository rRepository;
    private final scheduleRepository sRepository;
    private final RabbitMqEventBus eventBus;
    private final BookingReadJpaRepository br;
    private final ReviewReadJpaRepository rr;

    public DependencyInjectionConfig(@Autowired bookingRepository bRepository, @Autowired courtRepository cRepository, @Autowired reviewRepository rRepository, @Autowired scheduleRepository sRepository, @Autowired RabbitMqEventBus eventBus, @Autowired BookingReadJpaRepository br, @Autowired ReviewReadJpaRepository rr) {
        this.bRepository = bRepository;
        this.cRepository = cRepository;
        this.rRepository = rRepository;
        this.sRepository = sRepository;
        this.br = br;
        this.rr = rr;
        this.eventBus = eventBus;
    }
    @Bean
    public BookingService getBookingService(){
        CreateOrGetBookingHandler handler = new CreateOrGetBookingHandler(bRepository,eventBus);
        return new BookingService(handler);
    }
    @Bean
    public CourtService getCourtService(){
        SearchCourtsHandler handler = new SearchCourtsHandler(cRepository);
        return new CourtService(handler);
    }
    @Bean
    public ReviewService getReviewService(){
        CreateReviewHandler handler = new CreateReviewHandler(rRepository,eventBus);
        return new ReviewService(handler);
    }
    @Bean
    public ScheduleService getScheduleService(){
        GetCourtScheduleHandler handler = new GetCourtScheduleHandler(sRepository);
        return new ScheduleService(handler);
    }




}
