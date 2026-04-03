package org.example.src.application.query.handler;


import org.example.src.application.port.out.CourtRepository;
import org.example.src.application.query.SearchCourtsQuery;
import org.example.src.domain.model.aggregates.Court;
import org.example.src.infrastructure.adapter.out.entity.CourtReadEntity;


import java.util.List;

public class SearchCourtsHandler {

  private final CourtRepository courtSearchRepository;

  public SearchCourtsHandler(CourtRepository courtSearchRepository) {
    this.courtSearchRepository = courtSearchRepository;
  }

  public List<CourtReadEntity> handle(SearchCourtsQuery query) {
    return courtSearchRepository.searchByName(query.getText());
  }
}

