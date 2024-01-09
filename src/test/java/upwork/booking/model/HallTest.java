package upwork.booking.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class HallTest {

    @Test
    public void testHall() {
        Hall hall = new Hall();
        hall.setId(123);
        hall.setTheaterId(456);
        hall.setMaxX(20);
        hall.setMaxY(30);

        assertEquals(123, hall.getId());
        assertEquals(456, hall.getTheaterId());
        assertEquals(20, hall.getMaxX());
        assertEquals(30, hall.getMaxY());
    }
}