package org.example.src.application.query.handler;



import org.example.src.application.port.out.CourtRepository;
import org.example.src.application.query.SearchCourtsQuery;
import org.example.src.domain.model.aggregates.Court;
import org.example.src.domain.model.value_objects.CourtName;
import org.example.src.domain.model.value_objects.Location;
import org.example.src.infrastructure.adapter.out.entity.CourtReadEntity;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SearchCourtsHandlerTest {

  @Test
  public void testSearchCourts() {
    CourtRepository repo = mock(CourtRepository.class);
    SearchCourtsHandler handler = new SearchCourtsHandler(repo);

    SearchCourtsQuery query = new SearchCourtsQuery("tennis");

    List<CourtReadEntity> courts = List.of(
      new CourtReadEntity(),
      new CourtReadEntity()
    );

    when(repo.searchByName("tennis")).thenReturn(courts);

    List<CourtReadEntity> result = handler.handle(query);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("Court A", result.get(0).getName());
    assertEquals("Court B", result.get(1).getName());

    verify(repo, times(1)).searchByName("tennis");
  }
}
