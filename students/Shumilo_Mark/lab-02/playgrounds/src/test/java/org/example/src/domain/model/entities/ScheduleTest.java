package org.example.src.domain.model.entities;


import org.example.src.domain.model.events.ScheduleLockedEvent;
import org.example.src.domain.model.events.ScheduleUnlockedEvent;
import org.example.src.domain.model.events.TimeSlotAddedEvent;
import org.example.src.domain.model.value_objects.TimeSlot;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ScheduleTest {

  @Test
  public void shouldAddTimeSlot() {
    Schedule schedule = new Schedule(10L);

    TimeSlot slot = new TimeSlot(
      LocalDateTime.now(),
      LocalDateTime.now().plusHours(1)
    );

    schedule.addSlot(slot);

    assertEquals(1, schedule.getSlots().size());
    assertTrue(schedule.getEvents().stream().anyMatch(e -> e instanceof TimeSlotAddedEvent));
  }

  @Test
  public void shouldNotAddOverlappingSlot() {
    Schedule schedule = new Schedule(10L);

    TimeSlot slot1 = new TimeSlot(
      LocalDateTime.now(),
      LocalDateTime.now().plusHours(2)
    );

    TimeSlot slot2 = new TimeSlot(
      LocalDateTime.now().plusMinutes(30),
      LocalDateTime.now().plusHours(3)
    );

    schedule.addSlot(slot1);

    assertThrows(IllegalArgumentException.class, () -> schedule.addSlot(slot2));
  }

  @Test
  public void shouldLockSchedule() {
    Schedule schedule = new Schedule(10L);

    schedule.lock();

    assertTrue(schedule.isLocked());
    assertTrue(schedule.getEvents().stream().anyMatch(e -> e instanceof ScheduleLockedEvent));
  }

  @Test
  public void shouldNotAddSlotWhenLocked() {
    Schedule schedule = new Schedule(10L);
    schedule.lock();

    TimeSlot slot = new TimeSlot(
      LocalDateTime.now(),
      LocalDateTime.now().plusHours(1)
    );

    assertThrows(IllegalStateException.class, () -> schedule.addSlot(slot));
  }

  @Test
  public void shouldUnlockSchedule() {
    Schedule schedule = new Schedule(10L);
    schedule.lock();
    schedule.unlock();

    assertFalse(schedule.isLocked());
    assertTrue(schedule.getEvents().stream().anyMatch(e -> e instanceof ScheduleUnlockedEvent));
  }
}
