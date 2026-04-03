package org.example.src.application.projection;

import org.example.src.domain.model.events.CreateReviewEvent;
import org.example.src.infrastructure.adapter.out.entity.ReviewReadEntity;
import org.example.src.infrastructure.adapter.out.repository.ReviewReadJpaRepository;

public class ReviewProjection {

    private final ReviewReadJpaRepository repo;

    public ReviewProjection(ReviewReadJpaRepository repo) {
        this.repo = repo;
    }

    public void on(CreateReviewEvent e) {
        ReviewReadEntity r = new ReviewReadEntity();
        r.setId(e.getId());
        r.setUserId(e.getUserId());
        r.setCourtId(e.getCourtId());
        r.setText(e.getText().text());
        r.setRating(e.getRating().value());
        repo.save(r);
    }
}
