package org.example.src.application.port.in;

import org.example.src.domain.model.entities.Court;

import java.util.List;

//интерфейс для поиска площадок
public interface SearchCourtsUseCase {
  // получает площадки по строке поиска
  List<Court> search(String query);
}
