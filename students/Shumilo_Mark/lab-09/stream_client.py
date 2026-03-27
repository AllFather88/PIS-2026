import grpc
import system_pb2
import system_pb2_grpc
import asyncio

async def stream_active_bookings():
    async with grpc.aio.insecure_channel("localhost:9090") as channel:
        client = system_pb2_grpc.BookingServiceStub(channel)

        request = system_pb2.StreamActiveBookingsRequest(court_id=102)

        async for booking in client.StreamActiveBookings(request):
            print("Активная бронь:", booking)
            print("Ожидание....")

asyncio.run(stream_active_bookings())
