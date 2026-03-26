package org.example.src.application.port.in;

import org.example.src.domain.model.aggregates.Schedule;

//интерфейс для получения расписания площадки
public interface GetCourtScheduleUseCase {
  // получает расписание по id площадки
  Schedule getSchedule(Long courtId);
}
