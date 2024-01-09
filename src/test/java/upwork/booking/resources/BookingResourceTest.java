package upwork.booking.resources;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import upwork.booking.model.Booking;
import upwork.booking.model.Status;
import upwork.booking.services.BookingService;
import upwork.booking.utils.AllNeighborStrategy;
import upwork.booking.utils.ClosestNeighborStrategy;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
class BookingResourceTest {

    @InjectMock
    BookingService bookingService;

    @Test
    public void testGetNextAvailableTicketWithXY() {
        Booking b = new Booking();
        b.setId(45);
        Mockito.when(bookingService.getNextAvailableTicket(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(ClosestNeighborStrategy.class))).thenReturn(b);
        given()
                .queryParam("x", 5)
                .queryParam("y", 4)
                .when().get("/booking/ticket")
                .then()
                .statusCode(200)
                .body("id", equalTo(45));
    }

    @Test
    public void testGetNextAvailableTicketWithoutX() {
        given()
                .queryParam("y", 3)
                .when().get("/booking/ticket")
                .then()
                .statusCode(422);
    }

    @Test
    public void testGetNextAvailableTicketWithoutXY() {
        Booking b = new Booking();
        b.setId(100);
        Mockito.when(bookingService.getNextAvailableTicket(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(AllNeighborStrategy.class))).thenReturn(b);
        given()
                .when().get("/booking/ticket")
                .then()
                .statusCode(200)
                .body("id", equalTo(100));
    }


    @Test
    public void testGetNextAvailableTicketNotFound() {
        Mockito.when(bookingService.getNextAvailableTicket(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(AllNeighborStrategy.class))).thenReturn(null);
        given()
                .when().get("/booking/ticket")
                .then()
                .statusCode(404);
    }


    @Test
    public void testListAllReturnsEmpty() {
        Mockito.when(bookingService.listAll()).thenReturn(new ArrayList<>());
        given()
                .when().get("/booking")
                .then()
                .statusCode(200).body("$", hasSize(0));
    }

    @Test
    public void testListAllReturnsList() {
        var bookings = new ArrayList<Booking>();
        for (int i = 1; i < 4; i++) {
            var booking = new Booking();
            booking.setId(i);
            booking.setSeatId(i * 10 + i);
            booking.setSeatId(i * 10 + i);
            booking.setHallId(i * 10 + i);
            booking.setStatus(Status.Available);
            bookings.add(booking);
        }

        Mockito.when(bookingService.listAll()).thenReturn(bookings);
        given()
                .when().get("/booking")
                .then()
                .statusCode(200).body("$", hasSize(3));
    }

}