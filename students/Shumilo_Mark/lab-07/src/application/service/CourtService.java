package org.example.src.application.service;

import org.example.src.application.port.in.SearchCourtsUseCase;
import org.example.src.application.port.out.CourtRepository;
import org.example.src.application.query.SearchCourtsQuery;
import org.example.src.application.query.handler.SearchCourtsHandler;
import org.example.src.domain.model.aggregates.Court;
import org.example.src.infrastructure.adapter.out.entity.CourtReadEntity;

import java.util.List;

public class CourtService implements SearchCourtsUseCase {

  private final SearchCourtsHandler searchHandler;

  public CourtService(SearchCourtsHandler searchHandler) {
    this.searchHandler = searchHandler;
  }

  @Override
  public List<CourtReadEntity> search(String query) {
      return searchHandler.handle(new SearchCourtsQuery(query));
  }
}
