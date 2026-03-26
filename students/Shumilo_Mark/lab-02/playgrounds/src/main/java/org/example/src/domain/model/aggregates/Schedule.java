package org.example.src.domain.model.aggregates;

import org.example.src.domain.model.events.ScheduleLockedEvent;
import org.example.src.domain.model.events.ScheduleUnlockedEvent;
import org.example.src.domain.model.value_objects.TimeSlot;
import java.util.ArrayList;
import java.util.List;
import org.example.src.domain.model.events.TimeSlotAddedEvent;

// класс доменной сущности "расписание"
public class Schedule {

  private final Long courtId;
  private final List<TimeSlot> availableSlots = new ArrayList<>();
  private boolean locked = false;

  private final List<Object> events = new ArrayList<>();

  public Schedule(Long courtId) {
    if (courtId == null) throw new IllegalArgumentException("Court ID cannot be null");
    this.courtId = courtId;
  }

  public void addSlot(TimeSlot slot) {
    if (locked)
      throw new IllegalStateException("Schedule is locked and cannot be modified");

    for (TimeSlot s : availableSlots) {
      if (s.overlaps(slot)) {
        throw new IllegalArgumentException("TimeSlot overlaps with existing slot");
      }
    }

    availableSlots.add(slot);
    events.add(new TimeSlotAddedEvent(courtId, slot.start(), slot.end()));
  }

  public void lock() {
    if (locked)
      throw new IllegalStateException("Schedule is already locked");

    locked = true;
    events.add(new ScheduleLockedEvent(courtId));
  }

  public void unlock() {
    if (!locked)
      throw new IllegalStateException("Schedule is already unlocked");

    locked = false;
    events.add(new ScheduleUnlockedEvent(courtId));
  }

  public boolean isLocked() {
    return locked;
  }

  public List<TimeSlot> getSlots() {
    return List.copyOf(availableSlots);
  }

  public List<Object> getEvents() {
    return List.copyOf(events);
  }

    public Long getCourtId() {
      return courtId;
    }
}

