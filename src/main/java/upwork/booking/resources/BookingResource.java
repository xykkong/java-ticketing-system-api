package upwork.booking.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import upwork.booking.model.Booking;
import upwork.booking.services.BookingService;
import upwork.booking.utils.AllNeighborStrategy;
import upwork.booking.utils.ClosestNeighborStrategy;
import upwork.booking.utils.Constants;

@Path("/booking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {

    @Inject
    BookingService bookingService;

    @GET
    public Response listAll() {
        return Response.status(200).entity(bookingService.listAll()).build();
    }

    //Using GET to allow us easily test in the web browser.
    // Following REST principles, it should be a POST as we are also "creating" a booking
    @GET
    @Path("/ticket")
    public Response getNextAvailableTicket(@QueryParam("x") Integer x, @QueryParam("y") Integer y) {
        Booking booking;
        if (x != null && y != null) {
            booking = bookingService.getNextAvailableTicket(x, y, new ClosestNeighborStrategy());
        } else if (x == null && y == null) {
            x = (1 + Constants.MAX_X) / 2;
            y = (1 + Constants.MAX_Y) / 2;
            booking = bookingService.getNextAvailableTicket(x, y, new AllNeighborStrategy());
        } else {
            return Response.status(422).build();
        }
        if (booking == null) {
            return Response.status(404).entity("Not available").build();
        }
        return Response.status(200).entity(booking).build();
    }
}
