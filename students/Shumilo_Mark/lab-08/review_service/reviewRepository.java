package org.example.src.infrastructure.adapter.out;

import org.example.src.application.port.out.ReviewRepository;
import org.example.src.domain.model.aggregates.Review;

import org.example.src.infrastructure.adapter.out.mapers.ReviewMapper;
import org.example.src.infrastructure.adapter.out.repository.ReviewJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

// класс реализации обработки исхрдящих запросов для работы с отзывами
@Component
public class reviewRepository implements ReviewRepository {

  private final ReviewJpaRepository jpa;

  public reviewRepository(@Autowired ReviewJpaRepository jpa) {
    this.jpa = jpa;
  }

  @Override
  public Review save(Review review) {
    var entity = ReviewMapper.toEntity(review);
    var saved = jpa.save(entity);
    return ReviewMapper.toDomain(saved);
  }

  @Override
  public List<Review> findByCourtId(Long courtId) {
    return jpa.findByCourtId(courtId)
      .stream()
      .map(ReviewMapper::toDomain)
      .toList();
  }
}
