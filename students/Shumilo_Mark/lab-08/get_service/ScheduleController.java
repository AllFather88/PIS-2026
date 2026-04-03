package org.example.src.infrastructure.adapter.in;

// класс реализации обработки входящих запросов для работы с расписанием


import org.example.src.application.port.in.GetCourtScheduleUseCase;
import org.example.src.domain.model.aggregates.Schedule;
import org.example.src.infrastructure.adapter.out.entity.SheduleReadEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

  private final GetCourtScheduleUseCase scheduleService;

  public ScheduleController(GetCourtScheduleUseCase scheduleService) {
    this.scheduleService = scheduleService;
  }

  @GetMapping("/{courtId}")
  public SheduleReadEntity getSchedule(@PathVariable Long courtId) {
    return scheduleService.getSchedule(courtId);
  }
}
