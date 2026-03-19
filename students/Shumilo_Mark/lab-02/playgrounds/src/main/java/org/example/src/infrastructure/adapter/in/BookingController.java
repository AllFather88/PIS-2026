package org.example.src.infrastructure.adapter.in;


// класс реализации обработки входящих запросов для работы с бронями
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

  private final CreateBookingUseCase createBookingUseCase;

  public BookingController(CreateBookingUseCase createBookingUseCase) {
    this.createBookingUseCase = createBookingUseCase;
  }

  @PostMapping
  public Long create(@RequestBody CreateBookingRequest request) {
    return createBookingUseCase.create(request.toCommand());
  }
}
