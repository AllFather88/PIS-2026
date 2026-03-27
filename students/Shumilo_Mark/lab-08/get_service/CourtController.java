package org.example.src.infrastructure.adapter.in;

import org.example.src.application.port.in.SearchCourtsUseCase;
import org.example.src.domain.model.aggregates.Court;
import org.example.src.infrastructure.adapter.out.entity.CourtReadEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// класс реализации обработки входящих запросов для работы с площадками


import java.util.List;

@RestController
@RequestMapping("/api/courts")
public class CourtController {

  private final SearchCourtsUseCase searchService;

  public CourtController(SearchCourtsUseCase searchService) {
    this.searchService = searchService;
  }

  @GetMapping
  public List<CourtReadEntity> search(@RequestParam String query) {
    return searchService.search(query);
  }
}
