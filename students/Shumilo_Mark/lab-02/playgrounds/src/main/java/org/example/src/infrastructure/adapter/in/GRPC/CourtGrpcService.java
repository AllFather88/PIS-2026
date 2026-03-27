package org.example.src.infrastructure.adapter.in.GRPC;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.*;
import org.example.src.application.port.in.SearchCourtsUseCase;

@GrpcService
public class CourtGrpcService extends CourtServiceGrpc.CourtServiceImplBase {

    private final SearchCourtsUseCase searchService;

    public CourtGrpcService(SearchCourtsUseCase searchService) {
        this.searchService = searchService;
    }

    @Override
    public void searchCourts(SearchCourtsRequest request,
                             StreamObserver<SearchCourtsResponse> responseObserver) {

        var courts = searchService.search(request.getQuery());

        SearchCourtsResponse.Builder builder = SearchCourtsResponse.newBuilder();

        courts.forEach(c -> builder.addCourts(
                CourtDto.newBuilder()
                        .setCourtId(c.getId())
                        .setName(c.getName())
                        .setAddress(c.getLat().toString()+" "+ c.getLon().toString())
                        .build()
        ));

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
