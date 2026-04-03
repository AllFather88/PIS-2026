package org.example.src.application.query;



public final class GetCourtScheduleQuery {

  private final Long courtId;

  public GetCourtScheduleQuery(Long courtId) {
    if (courtId == null || courtId <= 0) {
      throw new IllegalArgumentException("courtId must be positive");
    }
    this.courtId = courtId;
  }

  public Long getCourtId() {
    return courtId;
  }
}
