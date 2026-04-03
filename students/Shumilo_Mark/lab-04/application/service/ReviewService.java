package org.example.src.application.service;

import org.example.src.application.command.CreateReviewCommand;
import org.example.src.application.command.handler.CreateReviewHandler;
import org.example.src.application.port.in.CreateReviewUseCase;
import org.example.src.domain.model.aggregates.Review;

public class ReviewService implements CreateReviewUseCase {

  private final CreateReviewHandler handler;

  public ReviewService(CreateReviewHandler handler) {
    this.handler = handler;
  }

  @Override
  public Review create(CreateReviewCommand command) {
    return handler.handle(command);
  }
}
