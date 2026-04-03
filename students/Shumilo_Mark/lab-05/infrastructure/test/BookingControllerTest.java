package org.example.src.infrastructure.config.springtest;

import org.example.src.application.port.out.BookingRepository;
import org.example.src.infrastructure.adapter.out.repository.BookingJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BookingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookingJpaRepository repository;

    @Test
    void createBookingViaHttp() throws Exception {
        String json = """
        {
          "courtId": 1,
          "userId": 42,
          "startTime": "2024-12-01T10:00:00",
          "endTime": "2024-12-01T11:00:00"
        }
        """;

        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        assertFalse(repository.findAll().isEmpty());
    }
}
