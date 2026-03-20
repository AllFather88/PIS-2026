package org.example.src.domain.model.entities;


import org.example.src.domain.model.events.ReviewEditedEvent;
import org.example.src.domain.model.value_objects.ReviewText;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ReviewTest {

  @Test
  public void shouldEditReview() {
    Review review = new Review(1L, 2L, 3L, 5, new ReviewText("Good court"));

    review.edit(new ReviewText("Great court!"));

    assertTrue(review.isEdited());
    assertTrue(review.getEvents().stream().anyMatch(e -> e instanceof ReviewEditedEvent));
  }

  @Test
  public void shouldNotEditWithSameText() {
    Review review = new Review(1L, 2L, 3L, 5, new ReviewText("noname"));

    assertThrows(IllegalStateException.class,
      () -> review.edit(new ReviewText("noname")));
  }
  @Test
  public void shouldCreateWithNegativeRating() {
    assertThrows(IllegalArgumentException.class,
      () -> new Review(1L, 2L, 3L, -5, new ReviewText("noname")));
  }

}
