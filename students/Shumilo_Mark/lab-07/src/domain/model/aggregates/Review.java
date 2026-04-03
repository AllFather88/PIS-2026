package org.example.src.domain.model.aggregates;

import lombok.Getter;
import org.example.src.application.eventbus.EventBus;
import org.example.src.domain.model.events.CreateReviewEvent;
import org.example.src.domain.model.value_objects.Rating;
import org.example.src.domain.model.value_objects.ReviewText;

import java.time.LocalDateTime;
import java.util.Objects;

import org.example.src.domain.model.events.ReviewEditedEvent;
import java.util.ArrayList;
import java.util.List;

// класс доменной сущности "отзыв"
@Getter
public class Review {

  private final Long id;
  private final Long courtId;
  private final Long userId;
  private final Rating rating;

  private ReviewText text;

  private final LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  private boolean edited = false;

  private final List<Object> events = new ArrayList<>();


  public Review(Long id, Long cId, Long userId, int rating, ReviewText text) {
    if (id == null) throw new IllegalArgumentException("Review ID cannot be null");
    if (cId == null) throw new IllegalArgumentException("Booking ID cannot be null");
    if (userId == null) throw new IllegalArgumentException("User ID cannot be null");
    if (rating < 1 || rating > 5) throw new IllegalArgumentException("Rating must be 1–5");

    this.id = id;
    this.courtId = cId;
    this.userId = userId;
    this.rating = new Rating(rating);
    this.text = text;

    this.createdAt = LocalDateTime.now();
    this.updatedAt = createdAt;
    events.add(new CreateReviewEvent(id,cId,userId,this.rating,text));
  }

  public void edit(ReviewText newText) {
    if (newText == null) throw new IllegalArgumentException("Review text cannot be null");
    if (newText.equals(this.text))
      throw new IllegalStateException("New text must be different");

    this.text = newText;
    this.edited = true;
    this.updatedAt = LocalDateTime.now();

    events.add(new ReviewEditedEvent(id, newText.text()));
  }


  public boolean isEdited() {
    return edited;
  }

  public List<Object> getEvents() {
    return List.copyOf(events);
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Review r && Objects.equals(id, r.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public long getId() {
    return id;
  }

  public int getRating() {
    return rating.value();
  }

  public String getText() {
    return text.text();
  }



}

