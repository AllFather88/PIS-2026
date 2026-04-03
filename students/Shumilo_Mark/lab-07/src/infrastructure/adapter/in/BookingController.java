package org.example.src.infrastructure.adapter.in;


// класс реализации обработки входящих запросов для работы с бронями



import lombok.Data;
import org.example.src.application.command.CreateBookingCommand;
import org.example.src.application.port.in.CreateBookingUseCase;
import org.example.src.domain.model.aggregates.Booking;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

  private final CreateBookingUseCase bookingService;

  public BookingController(CreateBookingUseCase bookingService) {
    this.bookingService = bookingService;
  }

  @PostMapping
  public Booking create(@RequestBody CreateBookingRequest request) {
    CreateBookingCommand cmd = new CreateBookingCommand(
      request.getCourtId(),
      request.getUserId(),
      request.getStartTime(),
      request.getEndTime()
    );
    return bookingService.create(cmd);
  }
}
@Data
class CreateBookingRequest {

  private Long courtId;
  private Long userId;

  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public CreateBookingRequest() {}

  public Long getCourtId() {
    return courtId;
  }

  public void setCourtId(Long courtId) {
    this.courtId = courtId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }
}





