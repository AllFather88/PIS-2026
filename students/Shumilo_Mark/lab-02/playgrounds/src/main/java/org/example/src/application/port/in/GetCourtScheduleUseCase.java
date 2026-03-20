package org.example.src.application.port.in;

import org.example.src.domain.model.entities.Schedule;

//интерфейс для получения расписания площадки
public interface GetCourtScheduleUseCase {
  // получает расписание по id площадки
  Schedule getSchedule(Long courtId);
}
