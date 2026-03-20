package org.example.src.domain.model.entities;


import org.example.src.domain.model.aggregates.Court;
import org.example.src.domain.model.events.CourtDeactivatedEvent;
import org.example.src.domain.model.events.CourtRenamedEvent;
import org.example.src.domain.model.value_objects.CourtName;
import org.example.src.domain.model.value_objects.Location;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourtTest {

  @Test
  public void shouldRenameCourt() {
    Court court = new Court(1L, new CourtName("Old"), new Location(10, 20));

    court.rename(new CourtName("New"));

    assertEquals("New", court.getName().value());
    assertTrue(court.getEvents().stream().anyMatch(e -> e instanceof CourtRenamedEvent));
  }

  @Test
  public void shouldNotRenameInactiveCourt() {
    Court court = new Court(1L, new CourtName("Old"), new Location(10, 20));
    court.deactivate();

    assertThrows(IllegalStateException.class, () -> court.rename(new CourtName("New")));
  }

  @Test
  public void shouldDeactivateCourt() {
    Court court = new Court(1L, new CourtName("Court"), new Location(10, 20));

    court.deactivate();

    assertFalse(court.isActive());
    assertTrue(court.getEvents().stream().anyMatch(e -> e instanceof CourtDeactivatedEvent));
  }
}

