package org.example.src.application.port.DTO;

import java.time.LocalDateTime;

//DTO для создания брони
public record CreateBookingCommand(
  Long courtId,
  Long userId,
  LocalDateTime start,
  LocalDateTime end
) {}
