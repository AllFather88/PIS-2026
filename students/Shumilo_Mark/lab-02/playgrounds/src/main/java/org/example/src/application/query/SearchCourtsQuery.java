package org.example.src.application.query;



public final class SearchCourtsQuery {

  private final String text;

  public SearchCourtsQuery(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
