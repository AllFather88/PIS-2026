package org.example.src.application.port.DTO;


//DTO для создания отзыва
public record CreateReviewCommand(
  Long courtId,
  Long userId,
  int rating,
  String text
) {}
