package org.example.src.application.command;

public final class CreateReviewCommand {
  private final Long courtId;
  private final Long userId;
  private final int rating;
  private final String text;

  public CreateReviewCommand(Long courtId, Long userId, int rating, String text) {
    if (courtId == null || courtId <= 0) throw new IllegalArgumentException();
    if (userId == null || userId <= 0) throw new IllegalArgumentException();
    if (rating < 1 || rating > 5) throw new IllegalArgumentException();
    if (text == null || text.isBlank()) throw new IllegalArgumentException();

    this.courtId = courtId;
    this.userId = userId;
    this.rating = rating;
    this.text = text;
  }

  public Long getCourtId() { return courtId; }
  public Long getUserId() { return userId; }
  public int getRating() { return rating; }
  public String getText() { return text; }
}
