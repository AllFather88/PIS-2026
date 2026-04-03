package org.example.src.application.command.handler;



import org.example.src.application.command.CreateReviewCommand;
import org.example.src.application.port.out.ReviewRepository;
import org.example.src.domain.model.aggregates.Review;
import org.example.src.domain.model.value_objects.ReviewText;


public class CreateReviewHandler {

  private final ReviewRepository reviewRepository;

  public CreateReviewHandler(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public Review handle(CreateReviewCommand command) {

    ReviewText text = new ReviewText(command.getText());
    Review review = new Review(
      1l,
      command.getCourtId(),
      command.getUserId(),
      command.getRating(),
      text
    );

    return reviewRepository.save(review);
  }
}

