package org.example.src.infrastructure.adapter.in;

// класс реализации обработки входящих запросов для работы с отзывами


import lombok.Data;
import org.example.src.application.command.CreateReviewCommand;
import org.example.src.application.port.in.CreateReviewUseCase;
import org.example.src.domain.model.aggregates.Review;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

  private final CreateReviewUseCase reviewService;

  public ReviewController(CreateReviewUseCase reviewService) {
    this.reviewService = reviewService;
  }

  @PostMapping
  public Review create(@RequestBody CreateReviewRequest request) {
    CreateReviewCommand cmd = new CreateReviewCommand(
      request.getCourtId(),
      request.getUserId(),
      request.getRating(),
      request.getText()
    );
    return reviewService.create(cmd);
  }
}
@Data
class CreateReviewRequest {
  private Long courtId;
  private Long userId;
  private int rating;
  private String text;

}


