package org.example.src.domain.model.events;

import java.time.Instant;

public class ReviewEditedEvent implements DomainEvent {

  private final Long reviewId;
  private final String newText;
  private final Instant occurredOn;

  public ReviewEditedEvent(Long reviewId, String newText) {
    this.reviewId = reviewId;
    this.newText = newText;
    this.occurredOn = Instant.now();
  }

  public Long getReviewId() {
    return reviewId;
  }

  public String getNewText() {
    return newText;
  }

  @Override
  public Instant occurredOn() {
    return occurredOn;
  }
}

