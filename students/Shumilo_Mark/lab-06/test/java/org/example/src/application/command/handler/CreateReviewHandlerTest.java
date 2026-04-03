package org.example.src.application.command.handler;



import org.example.src.application.command.CreateReviewCommand;
import org.example.src.application.port.out.ReviewRepository;
import org.example.src.domain.model.aggregates.Review;
import org.example.src.domain.model.value_objects.ReviewText;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreateReviewHandlerTest {

  @Test
  public void testCreateReview() {
    ReviewRepository repo = mock(ReviewRepository.class);

    CreateReviewHandler handler = new CreateReviewHandler(repo);

    CreateReviewCommand command = new CreateReviewCommand(
      5L, 7L, 4, "Nice court"
    );

    Review saved = new Review(1L, 5L, 7L, 4, new ReviewText("Nice court"));

    when(repo.save(any(Review.class))).thenReturn(saved);

    Review result = handler.handle(command);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals(4, result.getRating());
    assertEquals("Nice court", result.getText());

    verify(repo, times(1)).save(any(Review.class));
  }
}

