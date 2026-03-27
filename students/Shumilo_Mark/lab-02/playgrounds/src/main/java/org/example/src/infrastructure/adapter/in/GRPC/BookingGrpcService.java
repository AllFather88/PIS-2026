package org.example.src.infrastructure.adapter.in.GRPC;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.*;
import org.example.src.application.command.CreateBookingCommand;
import org.example.src.application.port.in.CreateBookingUseCase;

import java.time.LocalDateTime;


@GrpcService
public class BookingGrpcService extends BookingServiceGrpc.BookingServiceImplBase {

    private final CreateBookingUseCase bookingService;

    public BookingGrpcService(CreateBookingUseCase bookingService) {
        this.bookingService = bookingService;
    }



    @Override
    public void createBooking(org.example.grpc.CreateBookingRequest request, StreamObserver<BookingDto> responseObserver) {
        var cmd = new CreateBookingCommand(
                request.getCourtId(),
                request.getUserId(),
                LocalDateTime.parse(request.getStartTime()),
                LocalDateTime.parse(request.getEndTime())
        );

        var booking = bookingService.create(cmd);

        BookingDto response = BookingDto.newBuilder()
                .setBookingId(booking.getId())
                .setCourtId(booking.getCourtId())
                .setUserId(booking.getUserId())
                .setStartTime(booking.getSlot().start().toString())
                .setEndTime(booking.getSlot().end().toString())
                .setStatus(booking.getStatus().name())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    @Override
    public void streamActiveBookings(StreamActiveBookingsRequest request,
                                     StreamObserver<BookingDto> responseObserver) {
        var active = bookingService.getActiveBookings(request.getCourtId());
        System.out.println(active.size());
        active.forEach(b -> {
            BookingDto dto = BookingDto.newBuilder()
                    .setBookingId(b.getId())
                    .setCourtId(b.getCourtId())
                    .setUserId(b.getUserId())
                    .setStartTime(b.getStartTime().toString())
                    .setEndTime(b.getEndTime().toString())
                    .setStatus("Active")
                    .build();

            responseObserver.onNext(dto);
        });
        responseObserver.onCompleted();
    }

    @Override
    public void createBooking(CreateBookingRequest request, StreamObserver<BookingDto> responseObserver) {

    }

    @Override
    public void CreateBooking(CreateBookingRequest request, StreamObserver<BookingDto> responseObserver) {

    }
}

