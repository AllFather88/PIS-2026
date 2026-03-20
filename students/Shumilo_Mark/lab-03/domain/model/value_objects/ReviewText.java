package org.example.src.domain.model.value_objects;

public record ReviewText(String text) {
  public ReviewText {
    if (text == null || text.length() < 5)
      throw new IllegalArgumentException("Review must contain at least 5 characters");
  }


}
