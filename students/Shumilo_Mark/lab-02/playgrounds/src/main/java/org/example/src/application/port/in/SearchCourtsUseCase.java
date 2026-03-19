package org.example.src.application.port.in;

import org.example.src.domain.model.Court;
import org.example.src.domain.model.Review;

import java.util.List;

//интерфейс для поиска площадок
public interface SearchCourtsUseCase {
  // получает площадки по строке поиска
  List<Court> search(String query);
}
