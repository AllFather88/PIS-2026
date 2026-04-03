package org.example.src.infrastructure.config;

import org.example.src.application.command.handler.CreateBookingHandler;
import org.example.src.application.command.handler.CreateReviewHandler;
import org.example.src.application.port.in.SearchCourtsUseCase;
import org.example.src.application.port.out.CourtRepository;
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
import org.example.src.infrastructure.adapter.out.reviewRepository;
import org.example.src.infrastructure.adapter.out.scheduleRepository;
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

    public DependencyInjectionConfig(@Autowired bookingRepository bRepository,@Autowired courtRepository cRepository,@Autowired reviewRepository rRepository,@Autowired scheduleRepository sRepository) {
        this.bRepository = bRepository;
        this.cRepository = cRepository;
        this.rRepository = rRepository;
        this.sRepository = sRepository;
    }
    @Bean
    public BookingService getBookingService(){
        CreateBookingHandler handler = new CreateBookingHandler(bRepository);
        return new BookingService(handler);
    }
    @Bean
    public CourtService getCourtService(){
        SearchCourtsHandler handler = new SearchCourtsHandler(cRepository);
        return new CourtService(handler);
    }
    @Bean
    public ReviewService getReviewService(){
        CreateReviewHandler handler = new CreateReviewHandler(rRepository);
        return new ReviewService(handler);
    }
    @Bean
    public ScheduleService getScheduleService(){
        GetCourtScheduleHandler handler = new GetCourtScheduleHandler(sRepository);
        return new ScheduleService(handler);
    }
}
