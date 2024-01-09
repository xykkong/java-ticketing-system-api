package upwork.booking.services;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import org.graalvm.collections.Pair;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import upwork.booking.model.Booking;
import upwork.booking.model.Status;
import upwork.booking.repositories.BookingRepository;
import upwork.booking.utils.AllNeighborStrategy;
import upwork.booking.utils.Constants;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class BookingServiceTest {
    @Inject
    BookingService bookingService;

    @InjectMock
    BookingRepository bookingRepository;

    @Test
    public void testReserveIfAvailableWhenAllSeatAvailable() {
        var expectedNeighbors = new ArrayList<Booking>();
        for (int i = 1; i < 6; i++) {
            var booking = new Booking();
            booking.setId(i);
            booking.setSeatId(i * Constants.MAX_X + i);
            booking.setStatus(Status.Available);
            expectedNeighbors.add(booking);
        }

        PanacheQuery<Booking> mockQuery = Mockito.mock(PanacheQuery.class);
        Mockito.when(bookingRepository.find(Mockito.anyString(), Mockito.any(Object[].class))).thenReturn(mockQuery);
        Mockito.when(mockQuery.list()).thenReturn(expectedNeighbors);

        Booking reservedSeat = bookingService.reserveIfAvailable(3, 3);

        assertEquals(reservedSeat.getId(), 3);
        assertEquals(reservedSeat.getSeatId(), 33);
        assertEquals(reservedSeat.getStatus(), Status.Blocked);
    }

    @Test
    public void testReserveIfAvailableWhenAllSeatAvailableAndThrowErrorOnSave() {
        var expectedNeighbors = new ArrayList<Booking>();
        for (int i = 1; i < 6; i++) {
            var booking = new Booking();
            booking.setId(i);
            booking.setSeatId(i * Constants.MAX_X + i);
            booking.setStatus(Status.Available);
            expectedNeighbors.add(booking);
        }

        PanacheQuery<Booking> mockQuery = Mockito.mock(PanacheQuery.class);
        Mockito.when(bookingRepository.find(Mockito.anyString(), Mockito.any(Object[].class))).thenReturn(mockQuery);
        Mockito.when(mockQuery.list()).thenReturn(expectedNeighbors);
        //Mockito.when(bookingRepository.persistAndFlush(Mockito.mock(Booking.class))).thenThrow(OptimisticLockException.class);
        Mockito.doThrow(new OptimisticLockException()).when(bookingRepository).persistAndFlush(Mockito.any(Booking.class));

        Booking reservedSeat = bookingService.reserveIfAvailable(3, 3);
        assertNull(reservedSeat);
    }

    @Test
    public void testReserveIfAvailableWhenOneSeatBlocked() {
        var expectedNeighbors = new ArrayList<Booking>();
        for (int i = 1; i < 6; i++) {
            var booking = new Booking();
            booking.setId(i);
            booking.setSeatId(i * 10 + i);
            booking.setStatus(Status.Available);
            if (i == 1) {
                booking.setStatus(Status.Blocked);
            }
            expectedNeighbors.add(booking);
        }

        PanacheQuery<Booking> mockQuery = Mockito.mock(PanacheQuery.class);
        Mockito.when(bookingRepository.find(Mockito.anyString(), Mockito.any(Object[].class))).thenReturn(mockQuery);
        Mockito.when(mockQuery.list()).thenReturn(expectedNeighbors);

        // Test scenario where bookings are available
        Booking reservedSeat = bookingService.reserveIfAvailable(3, 3);
        assertNull(reservedSeat);
    }

    @Test
    public void testGetNextAvailableTicketReturnsValidBooking() {
        var bookings = new ArrayList<Booking>();
        Booking expectedBooking = null;
        for (int i = 1; i < 6; i++) {
            var booking = new Booking();
            booking.setId(i);
            booking.setSeatId(i * Constants.MAX_X + i);
            booking.setHallId(i);
            booking.setStatus(Status.Available);
            bookings.add(booking);
            if (i == 1) {
                expectedBooking = booking;
            }
        }

        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(bookingRepository.find(Mockito.anyString(), Mockito.any(Object[].class))).thenReturn(query);
        Mockito.when(query.list()).thenReturn(bookings);

        // Test cases
        Booking booking = bookingService.getNextAvailableTicket(1, 1, new AllNeighborStrategy());
        assertEquals(expectedBooking, booking);
    }

    @Test
    public void testGetNextAvailableTicketReturnsNull() {
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(bookingRepository.find(Mockito.anyString(), Mockito.any(Object[].class))).thenReturn(query);
        Mockito.when(query.list()).thenReturn(new ArrayList<Booking>());
        var neighbors = new ArrayList<Pair<Integer, Integer>>();
        neighbors.add(Pair.create(1, 1));
        neighbors.add(Pair.create(2, 2));
        neighbors.add(Pair.create(3, 3));
        AllNeighborStrategy neighborStrategyMock = Mockito.mock(AllNeighborStrategy.class);
        Mockito.when(neighborStrategyMock.listNeighbor(Mockito.anyInt(), Mockito.anyInt())).thenReturn(neighbors);

        // Test cases
        Booking availableBooking = bookingService.getNextAvailableTicket(5, 4, neighborStrategyMock);
        assertNull(availableBooking);
    }

    @Test
    public void testListAll() {
        var bookings = new ArrayList<Booking>();
        for (int i = 1; i < 6; i++) {
            var booking = new Booking();
            booking.setId(i);
            bookings.add(booking);
        }
        Mockito.when(bookingRepository.listAll()).thenReturn(bookings);

        // Test cases
        assertEquals(bookingService.listAll().size(), bookings.size());
    }
}
