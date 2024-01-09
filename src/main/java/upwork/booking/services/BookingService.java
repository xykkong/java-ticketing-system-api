package upwork.booking.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.graalvm.collections.Pair;
import upwork.booking.model.Booking;
import upwork.booking.model.Status;
import upwork.booking.repositories.BookingRepository;
import upwork.booking.utils.Constants;
import upwork.booking.utils.NeighborStrategy;
import upwork.booking.utils.SocialDistanceNeighborStrategy;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookingService {

    @Inject
    BookingRepository bookingRepository;

    public List<Booking> listAll() {
        return bookingRepository.listAll();
    }

    @Transactional
    public Booking reserveIfAvailable(Integer x, Integer y) {
        var seatId = (y * Constants.MAX_X) + x;
        var neighbors = new SocialDistanceNeighborStrategy().listNeighbor(x, y);
        var idsToFind = neighbors.stream().map(p -> (p.getRight() * Constants.MAX_X) + p.getLeft()).collect(Collectors.toList());
        idsToFind.add(seatId);

        // We could cache it, either in memory of using a external cache, so we don't need to always query the DB.
        List<Booking> bookings = bookingRepository.find("seatId IN (?1)", idsToFind).list();

        if (bookings.size() != 5 || !bookings.stream().allMatch(booking -> Status.Available.equals(booking.getStatus())))
            return null;

        for (var booking : bookings) {
            if (booking.getSeatId() == seatId) {
                booking.setStatus(Status.Blocked);
                // Persist the changes
                try {
                    // Update the entity in the database with optimistic locking
                    bookingRepository.persistAndFlush(booking);
                    return booking;
                } catch (OptimisticLockException ex) {
                    // Probably we want to retry the operation or start over, so user can choose another seat
                    System.out.println(ex.getMessage());
                    break;
                }
            }
        }
        return null;
    }

    public Booking getNextAvailableTicket(Integer x, Integer y, NeighborStrategy strategy) {
        var queue = new LinkedList<Pair<Integer, Integer>>();
        var visited = new HashSet<>();
        queue.add(Pair.create(x, y));

        while (!queue.isEmpty()) {
            var top = queue.remove();
            visited.add(top);
            x = top.getLeft();
            y = top.getRight();
            var booking = reserveIfAvailable(x, y);
            if (booking != null) {
                return booking;
            }
            for (var neighbor : strategy.listNeighbor(x, y)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
        return null;
    }
}