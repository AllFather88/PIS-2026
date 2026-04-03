package org.example.src.application.command;

import java.time.LocalDateTime;

public final class CreateBookingCommand {
  private final Long courtId;
  private final Long userId;
  private final LocalDateTime start;
  private final LocalDateTime end;

  public CreateBookingCommand(Long courtId, Long userId,
                              LocalDateTime start, LocalDateTime end) {
    if (courtId == null || courtId <= 0) throw new IllegalArgumentException();
    if (userId == null || userId <= 0) throw new IllegalArgumentException();
    if (start == null || end == null) throw new IllegalArgumentException();
    if (!end.isAfter(start)) throw new IllegalArgumentException();

    this.courtId = courtId;
    this.userId = userId;
    this.start = start;
    this.end = end;
  }

  public Long getCourtId() { return courtId; }
  public Long getUserId() { return userId; }
  public LocalDateTime getStart() { return start; }
  public LocalDateTime getEnd() { return end; }
}
