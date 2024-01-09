package upwork.booking.utils;

import org.graalvm.collections.Pair;

import java.util.ArrayList;
import java.util.List;


public class ClosestNeighborStrategy implements NeighborStrategy {

    /**
     * Get closest neighbor in direction towards the middle of the cinema hall
     *
     * @param x Position of the seat
     * @param y Position of the seat
     * @return List of closest neighbors
     */
    public List<Pair<Integer, Integer>> listNeighbor(Integer x, Integer y) {
        //
        int middleX = (1 + Constants.MAX_X) / 2;
        int middleY = (1 + Constants.MAX_Y) / 2;
        var neighbor = new ArrayList<Pair<Integer, Integer>>();
        if (x == middleX && y > middleY) {
            neighbor.add(Pair.create(x, y - 1));
        }
        if (x == middleX && y < middleY) {
            neighbor.add(Pair.create(x, y + 1));
        }
        if (x > middleX && y == middleY) {
            neighbor.add(Pair.create(x - 1, y));
        }
        if (x < middleX && y == middleY) {
            neighbor.add(Pair.create(x + 1, y));
        }
        if (x > middleX && y > middleY) {
            neighbor.add(Pair.create(x - 1, y - 1));
        }
        if (x < middleX && y < middleY) {
            neighbor.add(Pair.create(x + 1, y + 1));
        }
        if (x > middleX && y < middleY) {
            neighbor.add(Pair.create(x - 1, y + 1));
        }
        if (x < middleX && y > middleY) {
            neighbor.add(Pair.create(x + 1, y - 1));
        }
        return neighbor;
    }
}
