import grpc
import system_pb2
import system_pb2_grpc

def create_booking():
    channel = grpc.insecure_channel("localhost:9090")
    client = system_pb2_grpc.BookingServiceStub(channel)

    request = system_pb2.CreateBookingRequest(
        court_id=1,
        user_id=1,
        start_time="2026-03-27T18:00",
        end_time="2026-03-27T19:00"
    )

    response = client.CreateBooking(request)
    print("Создана бронь:", response)

if __name__ == "__main__":
    create_booking()
