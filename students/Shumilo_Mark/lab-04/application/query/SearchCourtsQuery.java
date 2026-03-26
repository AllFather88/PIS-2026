package org.example.src.application.query;



public final class SearchCourtsQuery {

  private final String text;

  public SearchCourtsQuery(String text) {
    if (text == null || text.isBlank()) {
      throw new IllegalArgumentException("Search text cannot be blank");
    }
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
