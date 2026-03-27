package org.example.src.infrastructure.adapter.in.GRPC;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateBookingRequest {

    private Long courtId;
    private Long userId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public CreateBookingRequest() {}

    public Long getCourtId() {
        return courtId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}