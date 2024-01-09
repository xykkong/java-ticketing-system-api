package upwork.booking.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TheaterTest {

    @Test
    public void testTheater() {
        Theater theater = new Theater();
        theater.setId(123);
        theater.setName("Theater 123");

        assertEquals(123, theater.getId());
        assertEquals("Theater 123", theater.getName());
    }
}
