package upwork.booking.repositories;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BookingRepositoryTest {
    @InjectMock
    BookingRepository bookingRepository;
}
