package org.example.src.infrastructure.adapter.out;

import org.example.src.application.port.out.ScheduleRepository;
import org.example.src.domain.model.aggregates.Schedule;
import org.example.src.infrastructure.adapter.out.entity.SheduleReadEntity;
import org.example.src.infrastructure.adapter.out.mapers.ScheduleMapper;
import org.example.src.infrastructure.adapter.out.repository.ScheduleJpaRepository;
import org.example.src.infrastructure.adapter.out.repository.ScheduleReadJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// класс реализации обработки исхрдящих запросов для работы с расписанием
@Component
public class scheduleRepository implements ScheduleRepository {

  private final ScheduleJpaRepository jpa;
  private final ScheduleReadJpaRepository ReadJpa;
  public scheduleRepository(@Autowired ScheduleJpaRepository jpa, @Autowired ScheduleReadJpaRepository readJpa) {
    this.jpa = jpa;
      ReadJpa = readJpa;
  }

  @Override
  public SheduleReadEntity findByCourtId(Long courtId) {
    return ReadJpa.findById(courtId)
      .orElse(null);
  }

  @Override
  public Schedule save(Schedule schedule) {
    var entity = ScheduleMapper.toEntity(schedule);
    var saved = jpa.save(entity);
    return ScheduleMapper.toDomain(saved);
  }
}
