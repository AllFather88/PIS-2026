package org.example.src.application.command.handler;



import org.example.src.application.command.CreateReviewCommand;
import org.example.src.application.eventbus.EventBus;
import org.example.src.application.port.out.ReviewRepository;
import org.example.src.domain.model.aggregates.Review;
import org.example.src.domain.model.value_objects.ReviewText;


public class CreateReviewHandler {

  private final ReviewRepository reviewRepository;
  private final EventBus events;

  public CreateReviewHandler(ReviewRepository reviewRepository,EventBus b) {
    this.reviewRepository = reviewRepository;
    this.events = b;
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
    events.publishAll(review.getEvents());
    return reviewRepository.save(review);
  }
}

