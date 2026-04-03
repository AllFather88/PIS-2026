package org.example.src.application.query.handler;


import org.example.src.application.query.GetCourtScheduleQuery;
import org.example.src.application.port.out.ScheduleRepository;
import org.example.src.domain.model.aggregates.Schedule;
import org.example.src.infrastructure.adapter.out.entity.SheduleReadEntity;


public class GetCourtScheduleHandler {

  private final ScheduleRepository scheduleRepository;

  public GetCourtScheduleHandler(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  public SheduleReadEntity handle(GetCourtScheduleQuery query) {
    return scheduleRepository.findByCourtId(query.getCourtId());
  }
}

