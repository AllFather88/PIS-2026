package org.example.src.domain.model.aggregates;


import java.util.Objects;


import org.example.src.domain.model.events.CourtDeactivatedEvent;
import org.example.src.domain.model.events.CourtRenamedEvent;
import org.example.src.domain.model.value_objects.CourtName;
import org.example.src.domain.model.value_objects.Location;


import java.util.ArrayList;
import java.util.List;

// класс доменной сущности "площадка"
public class Court {

  private final Long id;
  private CourtName name;
  private Location location;
  private boolean active = true;

  private final List<Object> events = new ArrayList<>();

  public Court(Long id, CourtName name, Location location) {
    if (id == null) throw new IllegalArgumentException("Court ID cannot be null");

    this.id = id;
    this.name = name;
    this.location = location;
  }

  public void deactivate() {
    if (!active) throw new IllegalStateException("Court already inactive");

    this.active = false;
    events.add(new CourtDeactivatedEvent(id));
  }

  public void rename(CourtName newName) {
    if (!active) throw new IllegalStateException("Cannot rename inactive court");

    this.name = newName;
    events.add(new CourtRenamedEvent(id, newName.value()));
  }

  public boolean isActive() { return active; }

  public List<Object> getEvents() {
    return List.copyOf(events);
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Court c && Objects.equals(id, c.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public CourtName getName() {
    return name;
  }
}


