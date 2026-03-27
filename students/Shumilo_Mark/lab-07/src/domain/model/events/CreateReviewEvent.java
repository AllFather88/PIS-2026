package org.example.src.domain.model.events;

import lombok.Data;
import org.example.src.domain.model.value_objects.Rating;
import org.example.src.domain.model.value_objects.ReviewText;

import java.time.Instant;

@Data
public class CreateReviewEvent implements DomainEvent {

    private final Long id;
    private final Long courtId;
    private final Long userId;
    private final Rating rating;
    private final Instant occurredOn;
    private final ReviewText text;
    public CreateReviewEvent(Long id, Long courtId, Long userId, Rating rating, ReviewText text) {
        this.occurredOn = Instant.now();
        this.id = id;
        this.courtId = courtId;
        this.userId = userId;
        this.rating = rating;
        this.text = text;
    }


    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}
