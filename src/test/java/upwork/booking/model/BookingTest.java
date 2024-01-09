package upwork.booking.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class BookingTest {

    @Test
    public void testBooking() {
        Booking booking = new Booking();
        booking.setId(123);
        booking.setHallId(134);
        booking.setSeatId(22);
        booking.setStatus(Status.Locked);
        booking.setVersion(11L);

        assertEquals(123, booking.getId());
        assertEquals(134, booking.getHallId());
        assertEquals(22, booking.getSeatId());
        assertEquals(Status.Locked, booking.getStatus());
        assertEquals(11L, booking.getVersion());
    }
}
