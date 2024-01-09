package upwork.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Example of how to model a Hall. Currently, it is not in use.
 */
@Entity
public class Hall {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer theaterId;
    private Integer maxX;
    private Integer maxY;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Integer theaterId) {
        this.theaterId = theaterId;
    }

    public Integer getMaxX() {
        return maxX;
    }

    public void setMaxX(Integer maxX) {
        this.maxX = maxX;
    }

    public Integer getMaxY() {
        return maxY;
    }

    public void setMaxY(Integer maxY) {
        this.maxY = maxY;
    }
}
