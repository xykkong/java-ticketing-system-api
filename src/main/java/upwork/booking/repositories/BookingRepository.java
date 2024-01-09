package upwork.booking.repositories;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import upwork.booking.model.Booking;

@ApplicationScoped
public class BookingRepository implements PanacheRepositoryBase<Booking, Integer> {

}