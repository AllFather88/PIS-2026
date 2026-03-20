package org.example.src.application.port.in;

import org.example.src.application.port.DTO.CreateReviewCommand;
import org.example.src.domain.model.entities.Review;

//интерфейс для создания отзывов
public interface CreateReviewUseCase {
  // создаёт отзыв
  Review create(CreateReviewCommand command);
}
