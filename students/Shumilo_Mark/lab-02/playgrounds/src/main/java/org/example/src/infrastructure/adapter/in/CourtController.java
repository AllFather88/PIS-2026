package org.example.src.infrastructure.adapter.in;

import org.example.src.application.port.in.SearchCourtsUseCase;
import org.example.src.domain.model.Court;

import java.util.List;

// класс реализации обработки входящих запросов для работы с площадками
public class CourtController {

  private final SearchCourtsUseCase searchCourtsUseCase;

  public CourtController(SearchCourtsUseCase searchCourtsUseCase) {
    this.searchCourtsUseCase = searchCourtsUseCase;
  }

  public List<Court> search(String query) {
    return searchCourtsUseCase.search(query);
  }
}
