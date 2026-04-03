package org.example.src.application.query.handler;


import org.example.src.application.port.out.ScheduleRepository;
import org.example.src.application.query.GetCourtScheduleQuery;
import org.example.src.domain.model.aggregates.Schedule;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GetCourtScheduleHandlerTest {

  @Test
  public void testGetSchedule() {
    ScheduleRepository repo = mock(ScheduleRepository.class);
    GetCourtScheduleHandler handler = new GetCourtScheduleHandler(repo);

    GetCourtScheduleQuery query = new GetCourtScheduleQuery(10L);

    Schedule schedule = new Schedule(10L);

    when(repo.findByCourtId(10L)).thenReturn(schedule);

    Schedule result = handler.handle(query);

    assertNotNull(result);
    assertEquals(Long.valueOf(10L), result.getCourtId());
    assertTrue(result.getSlots().isEmpty());

    verify(repo, times(1)).findByCourtId(10L);
  }
}
