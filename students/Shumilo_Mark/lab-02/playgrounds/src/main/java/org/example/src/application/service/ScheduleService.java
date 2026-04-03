package org.example.src.application.service;

import org.example.src.application.port.in.GetCourtScheduleUseCase;
import org.example.src.application.query.GetCourtScheduleQuery;
import org.example.src.application.query.handler.GetCourtScheduleHandler;
import org.example.src.domain.model.aggregates.Schedule;
import org.example.src.infrastructure.adapter.out.entity.SheduleReadEntity;

public class ScheduleService implements GetCourtScheduleUseCase {
  private final GetCourtScheduleHandler handler;

  public ScheduleService(GetCourtScheduleHandler handler) {
    this.handler = handler;
  }
  @Override
  public Schedule getSchedule(Long courtId) {
      return handler.handle(new GetCourtScheduleQuery(courtId));
  }
}
