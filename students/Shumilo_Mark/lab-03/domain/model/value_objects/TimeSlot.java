package org.example.src.domain.model.value_objects;

import java.time.LocalDateTime;

public record TimeSlot(LocalDateTime start, LocalDateTime end) {

  public TimeSlot {
    if (start == null || end == null)
      throw new IllegalArgumentException("Start and end cannot be null");
    if (!start.isBefore(end))
      throw new IllegalArgumentException("Start must be before end");
  }

  public boolean overlaps(TimeSlot other) {
    return start.isBefore(other.end) && other.start.isBefore(end);
  }
}
