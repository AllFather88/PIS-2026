package org.example.src.application.port.in;

import org.example.src.domain.model.aggregates.Schedule;
import org.example.src.infrastructure.adapter.out.entity.SheduleReadEntity;

//интерфейс для получения расписания площадки
public interface GetCourtScheduleUseCase {
  // получает расписание по id площадки
  SheduleReadEntity getSchedule(Long courtId);
}
