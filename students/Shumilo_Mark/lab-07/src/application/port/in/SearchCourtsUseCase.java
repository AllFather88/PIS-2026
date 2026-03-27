package org.example.src.application.port.in;

import org.example.src.domain.model.aggregates.Court;
import org.example.src.infrastructure.adapter.out.entity.CourtReadEntity;

import java.util.List;

//интерфейс для поиска площадок
public interface SearchCourtsUseCase {
  // получает площадки по строке поиска
  List<CourtReadEntity> search(String query);
}
