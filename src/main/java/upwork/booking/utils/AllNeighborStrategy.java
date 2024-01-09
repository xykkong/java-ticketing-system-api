package upwork.booking.utils;

import org.graalvm.collections.Pair;

import java.util.ArrayList;
import java.util.List;


public class AllNeighborStrategy implements NeighborStrategy {

    /**
     * Get all neighbors
     *
     * @param x Position of the seat
     * @param y Position of the seat
     * @return List of all neighbors
     */
    public List<Pair<Integer, Integer>> listNeighbor(Integer x, Integer y) {
        var neighbor = new ArrayList<Pair<Integer, Integer>>();
        neighbor.add(Pair.create(x - 1, y - 1));
        neighbor.add(Pair.create(x - 1, y));
        neighbor.add(Pair.create(x - 1, y + 1));
        neighbor.add(Pair.create(x, y - 1));
        neighbor.add(Pair.create(x, y + 1));
        neighbor.add(Pair.create(x + 1, y - 1));
        neighbor.add(Pair.create(x + 1, y));
        neighbor.add(Pair.create(x + 1, y + 1));
        return neighbor;
    }
}
