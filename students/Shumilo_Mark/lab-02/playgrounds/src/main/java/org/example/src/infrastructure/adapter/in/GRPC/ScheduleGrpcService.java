package org.example.src.infrastructure.adapter.in.GRPC;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.*;
import org.example.src.application.port.in.GetCourtScheduleUseCase;
import org.example.src.domain.model.aggregates.Schedule;

@GrpcService
public class ScheduleGrpcService extends ScheduleServiceGrpc.ScheduleServiceImplBase {

    private final GetCourtScheduleUseCase scheduleService;

    public ScheduleGrpcService(GetCourtScheduleUseCase scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public void getSchedule(GetScheduleRequest request,
                            StreamObserver<ScheduleResponse> responseObserver) {

        Schedule schedule = scheduleService.getSchedule(request.getCourtId());

        ScheduleResponse.Builder builder = ScheduleResponse.newBuilder();

        schedule.getSlots().forEach(slot -> {
            builder.addSlots(TimeSlot.newBuilder()
                    .setStart(slot.start().toString())
                    .setEnd(slot.end().toString())
                    .setAvailable(true)
                    .build());
        });

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
