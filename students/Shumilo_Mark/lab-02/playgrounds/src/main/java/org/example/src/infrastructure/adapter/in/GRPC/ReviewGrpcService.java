package org.example.src.infrastructure.adapter.in.GRPC;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.*;
import org.example.src.application.port.in.CreateReviewUseCase;
import org.example.src.application.command.CreateReviewCommand;

@GrpcService
public class ReviewGrpcService extends ReviewServiceGrpc.ReviewServiceImplBase {

    private final CreateReviewUseCase reviewService;

    public ReviewGrpcService(CreateReviewUseCase reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void createReview(CreateReviewRequest request,
                             StreamObserver<ReviewDto> responseObserver) {

        var cmd = new CreateReviewCommand(
                request.getCourtId(),
                request.getUserId(),
                request.getRating(),
                request.getText()
        );

        var review = reviewService.create(cmd);

        ReviewDto response = ReviewDto.newBuilder()
                .setReviewId(review.getId())
                .setCourtId(review.getCourtId())
                .setUserId(review.getUserId())
                .setRating(review.getRating())
                .setText(review.getText())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

