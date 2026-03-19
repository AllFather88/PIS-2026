package org.example.src.application.service;

import org.example.src.application.port.in.SearchCourtsUseCase;
import org.example.src.application.port.out.CourtRepository;
import org.example.src.domain.model.Court;

import java.util.List;

public class CourtService implements SearchCourtsUseCase {

  private final CourtRepository courtRepository;

  public CourtService(CourtRepository courtRepository) {
    this.courtRepository = courtRepository;
  }

  @Override
  public List<Court> search(String query) {
    // TODO: реализовать в Lab #4
    // 1. Вызвать courtRepository.searchByName(query)
    // 2. Вернуть результат
    throw new UnsupportedOperationException("Будет реализовано в Lab #4");
  }
}
