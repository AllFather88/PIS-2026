package org.example.src.infrastructure.adapter.out;



import org.example.src.application.port.out.CourtRepository;
import org.example.src.domain.model.aggregates.Court;
import org.example.src.infrastructure.adapter.out.entity.CourtReadEntity;
import org.example.src.infrastructure.adapter.out.mapers.CourtMapper;
import org.example.src.infrastructure.adapter.out.repository.CourtJpaRepository;
import org.example.src.infrastructure.adapter.out.repository.CourtReadJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// класс реализации обработки исхрдящих запросов для работы с площадками
@Component
public class courtRepository implements CourtRepository {

  private final CourtJpaRepository jpa;
  private final CourtReadJpaRepository readJpa;
  public courtRepository(@Autowired CourtJpaRepository jpa,@Autowired CourtReadJpaRepository readJpa) {
      this.jpa = jpa;
      this.readJpa = readJpa;
  }

  @Override
  public Court findById(Long id) {
    return jpa.findById(id)
      .map(CourtMapper::toDomain)
      .orElse(null);
  }

  @Override
  public List<CourtReadEntity> searchByName(String query) {
    if(query == null || query.isEmpty()){
      return readJpa.findAll()
              .stream()

              .toList();
    }
    return readJpa.findByNameContainingIgnoreCase(query)
      .stream()
      .toList();
  }

  @Override
  public Court save(Court court) {
    var entity = CourtMapper.toEntity(court);
    var saved = jpa.save(entity);
    return CourtMapper.toDomain(saved);
  }
}


