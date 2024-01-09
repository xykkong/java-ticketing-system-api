package upwork.booking.utils;

import org.graalvm.collections.Pair;

import java.util.List;

public interface NeighborStrategy {

    /**
     * @param x Position of the seat
     * @param y Position of the seat
     * @return List of closest neighbors
     */
    List<Pair<Integer, Integer>> listNeighbor(Integer x, Integer y);
}
