package org.example.src.domain.model.value_objects;

public record CourtName(String value) {
  public CourtName {
    if (value == null || value.isBlank())
      throw new IllegalArgumentException("Court name cannot be empty");
  }
}
